package methods;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {
    String numberRegEx = "[0-9]*";

    @Override
    public void replaceText(int start, int eng, String text) {
        String oldValue = getText();
        if ((validate(text))) {
            super.replaceText(start, eng, text);
            String newText = super.getText();
            if (!validate(newText)) {
                super.setText(oldValue);
            }

        }
    }

    private boolean validate(String text) {
        return ("".equals(text) || text.matches(numberRegEx));
    }
}
