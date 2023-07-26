package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.QuestionMapperBak;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionMapperBak questionMapperBak;

    public PaginationDTO list(Integer page, Integer size) {
        // 计算分页获取数据的偏移量
        Integer offset = size * (page - 1);
        //List<Question> questions = questionMapperBak.list(offset, size);

        // 使用自动生成的Mapper不清楚什么原因没有返回description字段
        // selectByExampleWithRowbounds不会返回大字段如text\blob，要改成使用selectByExampleWithBLOBsWithRowbounds方法
        //List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        // 查询数据表总共有多少条数据
        //Integer totalCount = questionMapper.count();
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        paginationDTO.setQuestions(questionDTOList);
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);

            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andIdEqualTo(Integer.valueOf(question.getCreator()+""));
            User user = userMapper.selectByPrimaryKey(Integer.valueOf(question.getCreator()+""));
            // User user = userMapper.selectById(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }

    public PaginationDTO list(User user, Integer page, Integer size) {
        // 计算分页获取数据的偏移量
        Integer offset = size * (page - 1);

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(user.getId());
        List<Question> questions = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds(offset, size));
        //List<Question> questions = questionMapperBak.listByUserId(Long.valueOf(user.getId()+""), offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        // 查询数据表总共有多少条数据
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                       .andCreatorEqualTo(user.getId());
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        //Integer totalCount = questionMapper.countByUserId(Long.valueOf(user.getId()+""));
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        paginationDTO.setQuestions(questionDTOList);
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        //Question question = questionMapper.queryById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        //User user = userMapper.selectById(question.getCreator());
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() != null) {
            //questionMapper.update(question);
            questionMapper.updateByPrimaryKeySelective(question);
        } else {
            questionMapper.insert(question);
        }
    }
}
