package utils;

import javax.swing.*;

public class LancaMensagem {

    public  void lancaErro(final String erro){
        JOptionPane.showMessageDialog(null,
                erro,
                "ERRO",JOptionPane.ERROR_MESSAGE);
    }
    public void lancaSucesso(final String sucesso){
        JOptionPane.showMessageDialog(null,
                sucesso);
    }
}
