package life.majiang.community.service;

import life.majiang.community.dto.NotificationDTO;
import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.enums.NotificationStatusEnum;
import life.majiang.community.enums.NotificationTypeEnum;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.NotificationMapper;
import life.majiang.community.model.Notification;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long receiver, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        List<NotificationDTO> notificationDTOS = getNotificationDTOS(notificationMapper.selectByReceiver(receiver, offset, size));

        Integer totalCount = notificationMapper.count(receiver);
        return getPaginationDTO(totalCount, page, size, notificationDTOS);
    }

    private List<NotificationDTO> getNotificationDTOS(List<Notification> notifications) {
        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }

        return notificationDTOS;
    }

    private PaginationDTO getPaginationDTO(Integer totalCount, Integer page, Integer size, List<NotificationDTO> notificationDTOS) {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.init(totalCount, page, size);
        paginationDTO.setData(notificationDTOS.stream()
                .map(o -> (Object) o)
                .collect(Collectors.toList()));

        return paginationDTO;
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectById(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        // 将通知状态置为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateStatus(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }

    public Integer unreadCount(Long receiver) {
        return notificationMapper.unreadCount(receiver);
    }
}
