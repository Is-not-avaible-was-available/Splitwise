package com.scaler.Splitwise.Command;

import com.scaler.Splitwise.Controller.SettleUpController;
import com.scaler.Splitwise.DTO.SettleUpUserRequestDTO;
import com.scaler.Splitwise.DTO.SettleUpUserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SettleUpUserCommand implements Command{
    private SettleUpController settleUpController;

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size()==2 && words.get(0).equals(CommandKeyword.settleUp);
    }

    @Override
    public void execute(String input) {
        List<String>words = List.of(input.split(" "));
        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDTO requestDTO = new SettleUpUserRequestDTO();
        requestDTO.setUserId(userId);
        SettleUpUserResponseDTO responseDTO = settleUpController.settleUpUser(requestDTO);
    }
}
