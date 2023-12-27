package com.scaler.Splitwise.Command;

import org.springframework.stereotype.Component;

@Component
public interface Command {

    public void execute(String  input);
    public boolean matches(String input);

}
