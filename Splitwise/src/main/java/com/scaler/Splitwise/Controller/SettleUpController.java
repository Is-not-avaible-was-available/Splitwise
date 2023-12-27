package com.scaler.Splitwise.Controller;

import com.scaler.Splitwise.DTO.*;
import com.scaler.Splitwise.Exceptions.UserNotFoundException;
import com.scaler.Splitwise.Services.SettleUpService;
import com.scaler.Splitwise.Services.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;
    @Autowired

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDTO settleUpUser(SettleUpUserRequestDTO request){
        SettleUpUserResponseDTO response = new SettleUpUserResponseDTO();

        List<Transaction> transactions;
        try{
            transactions = settleUpService.settleUpUser(request.getUserId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setTransactions(transactions);
        }catch (UserNotFoundException ex){
            response.setFailureMessage("Failed!");
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO request){
        SettleUpGroupResponseDTO response = new SettleUpGroupResponseDTO();

        List<Transaction>transactions;

        try {
            transactions = settleUpService.settleUpGroup(request.getGroupId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setTransactions(transactions);
        }catch (Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureMessage("Failed!");
        }
        return response;
    }
}
