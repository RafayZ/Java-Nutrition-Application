package main.app.utils;

import javax.swing.*;
import java.awt.*;

public class MealDisplayPanel extends JPanel {

    JLabel mealTypePrompt = new JLabel("Food items in this meal: ");
    JLabel foodItemLabels = new JLabel("");

    public MealDisplayPanel() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int textInputHeight = (int) (screenSize.height * 0.10); // 20% of screen width

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initialise(textInputHeight);
    }

    public boolean noFoodCheck() {
        return foodItemLabels.getText().isEmpty();
    }

    private void initialise(int textInputHeight) {

        Font smallerFont = new Font("Arial", Font.BOLD, 15);
        mealTypePrompt.setFont(smallerFont);

        mealTypePrompt.setPreferredSize(new Dimension(mealTypePrompt.getPreferredSize().width, textInputHeight));
        mealTypePrompt.setMaximumSize(new Dimension(mealTypePrompt.getMaximumSize().width, textInputHeight));
        add(mealTypePrompt);
        add(foodItemLabels);
    }

}
