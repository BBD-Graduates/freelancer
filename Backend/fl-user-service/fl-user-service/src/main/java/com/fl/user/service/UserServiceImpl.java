package com.fl.user.service;
import com.fl.user.config.Constant;
import com.fl.user.model.request.UserRequest;
import com.fl.user.repository.DbQueries;
import com.fl.user.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;
    @Override
    public String insertUser(UserRequest userRequest) {
        try {
            int insertStatus=jdbcTemplate.update(dbQueries.getAddUser(),userRequest.getEmail());
            if (insertStatus > 0) {
                return Constant.REGISTERED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
