package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        return getQuestionDTOS(questionMapper.list());
    }

    public List<QuestionDTO> pageList(Integer page, Integer size) {
        // 计算分页偏移量
        Integer offset = (page - 1) * size;
        return getQuestionDTOS( questionMapper.listPage(offset, size));
    }

    public PaginationDTO list(Integer page, Integer size) {
        // 获得要展现页的数据
        Integer offset = (page - 1) * size;
        List<QuestionDTO> questionDTOS = getQuestionDTOS(questionMapper.listPage(offset, size));

        Integer totalCount = questionMapper.count();
        return getPaginationDTO(totalCount, page, size, questionDTOS);
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        List<QuestionDTO> questionDTOS = getQuestionDTOS(questionMapper.userListPage(userId, offset, size));

        Integer totalCount = questionMapper.userCount(userId);
        return getPaginationDTO(totalCount, page, size, questionDTOS);
    }


    private List<QuestionDTO> getQuestionDTOS(List<Question> questions) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(userMapper.getById(question.getCreator()));
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

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.getById(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.getById(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() != null) {
            int updated = questionMapper.update(question);
            if (updated == 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        } else {
            questionMapper.insert(question);
        }
    }

    public void incView(Long id) {
        questionMapper.incView(id);
    }
}
