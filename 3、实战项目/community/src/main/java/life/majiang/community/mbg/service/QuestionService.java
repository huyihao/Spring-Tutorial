package life.majiang.community.mbg.service;

import life.majiang.community.mbg.dto.PaginationDTO;
import life.majiang.community.mbg.dto.QuestionDTO;
import life.majiang.community.mbg.mapper.QuestionMapper;
import life.majiang.community.mbg.mapper.UserMapper;
import life.majiang.community.mbg.model.Question;
import life.majiang.community.mbg.model.QuestionExample;
import life.majiang.community.mbg.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("MbgQuestionService")
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        QuestionExample example = new QuestionExample();
        example.createCriteria();
        return getQuestionDTOS(questionMapper.selectByExample(example));
    }

    public List<QuestionDTO> pageList(Integer page, Integer size) {
        // 计算分页偏移量
        Integer offset = (page - 1) * size;
        QuestionExample example = new QuestionExample();
        example.createCriteria();
        return getQuestionDTOS(questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size)));
    }

    public PaginationDTO list(Integer page, Integer size) {
        // 获得要展现页的数据
        Integer offset = (page - 1) * size;
        QuestionExample example = new QuestionExample();
        example.createCriteria();
        List<QuestionDTO> questionDTOS = getQuestionDTOS(questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size)));

        Integer totalCount = Integer.valueOf(questionMapper.countByExample(example) + "");
        return getPaginationDTO(totalCount, page, size, questionDTOS);
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(Integer.valueOf(userId + ""));
        List<QuestionDTO> questionDTOS = getQuestionDTOS(questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size)));

        Integer totalCount = Integer.valueOf(questionMapper.countByExample(example) + "");
        return getPaginationDTO(totalCount, page, size, questionDTOS);
    }


    private List<QuestionDTO> getQuestionDTOS(List<Question> questions) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(userMapper.selectByPrimaryKey(Long.valueOf(question.getCreator())));
            questionDTOS.add(questionDTO);
        }

        return questionDTOS;
    }

    private PaginationDTO getPaginationDTO(Integer totalCount, Integer page, Integer size, List<QuestionDTO> questionDTOS) {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.init(totalCount, page, size);
        paginationDTO.setData(questionDTOS.stream()
                .map(o -> (Object) o)
                .collect(Collectors.toList()));

        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey((long) question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() != null) {
            questionMapper.updateByPrimaryKeySelective(question);
        } else {
            questionMapper.insert(question);
        }
    }
}
