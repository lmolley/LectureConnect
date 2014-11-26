package Quiz;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import Quiz.*;



public class quizInit extends JDialog
{
    JButton nextButton, prevButton;
    JButton submitButton;
    JLabel background;
    QBox[] qBoxArr;
    JPanel qPan;
    Quiz quiz;
    public int qNumber, numQuestions, numCorrect;
    
    CardLayout cardLayout;
    
    
   
    
    public quizInit(JFrame mainFrame_in){ //server side, chooses file
        super(mainFrame_in, "Quiz!", true);
        quiz = new Quiz(this);
        //setupFrame();
    }
    
    public quizInit(JFrame mainFrame_in, String entireQuizFile){
        super(mainFrame_in, "Quiz!", true);
        quiz = new Quiz(entireQuizFile);
        setupFrame();
    }
    
    private void setupFrame(){
        
        this.setLayout(new BorderLayout());
        try{ 
            BufferedImage img = ImageIO.read(new File("src/images/logo.png")); 
            background = new JLabel(new ImageIcon(img));
            }
        catch (IOException ioe){
            System.out.println("couldnt load image");
        }
        this.add(background, BorderLayout.NORTH);
        
        qNumber = 1;
        numQuestions = quiz.questionArr.length;
        qBoxArr = new QBox[numQuestions];
        //prepare arr
        
        cardLayout = new CardLayout();
        qPan = new JPanel(cardLayout);
        for (int i = 0; i < numQuestions; i++){
            qBoxArr[i] = new QBox(i);
            qPan.add(qBoxArr[i].box, "Question " + i);
        }

        /////
        updateQuestionPanel();
        this.add(qPan, BorderLayout.CENTER);
        
        QuizListeners myListener = new QuizListeners(nextButton, prevButton, submitButton, this);
        nextButton = new JButton("Next Question");
        prevButton = new JButton("Previous Question");
        submitButton = new JButton("Submit Answers");
        
        nextButton.addActionListener(myListener);
        prevButton.addActionListener(myListener);
        submitButton.addActionListener(myListener);
        
        Box buttonBox = new Box(BoxLayout.LINE_AXIS);
        buttonBox.add(nextButton);
        buttonBox.add(prevButton);
        buttonBox.add(submitButton);
        this.add(buttonBox, BorderLayout.SOUTH);
        
        
        this.pack();
        this.setVisible(true);
    }
    
    public void updateQuestionPanel(){
        if(qNumber < 0 || qNumber >= quiz.questionArr.length){
            System.out.println("out of bounds question Number");
        }
        cardLayout.show(qPan, "Question " + qNumber);
        this.pack();
    }
    
    public void prepareQBoxArr(){
        int index = 0;
        for (QBox b : qBoxArr){
            b = new QBox(index);
            index++;
        }
    }
    
    public void advanceQuestion(int next){//either 1 or -1
        if(next < -1 || next > 1){
            return;
        }
        qNumber += next;
        if(qNumber >= quiz.questionArr.length){
            qNumber = 0;
        }
        else if (qNumber < 0){
            qNumber = quiz.questionArr.length - 1;
        }
    }
    
    public void gradeQuiz(){
        int reply = JOptionPane.showConfirmDialog(this, 
                "Are you sure youre done?", "Confirm Submit", 
                JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION){
            numCorrect = 0;
            for (QBox qb : qBoxArr){
                if(qb.isCorrect()){
                    numCorrect++;
                }
            }
            setVisible(false);
        }
    }
    
    
    
    public class QBox{
        JLabel questionLabel;
        List<JRadioButton> radioButtonList;
        ButtonGroup choiceButtonGroup;
        Box box;
        
        int correctChoiceIndex;
        
        public QBox(int index){
            if (index < 0 || index > numQuestions){
                System.out.println("out of bounds question Number");
            }
            radioButtonList = new ArrayList<JRadioButton>();
            box = new Box(BoxLayout.PAGE_AXIS);
            questionLabel = new JLabel(quiz.questionArr[index].getQ());
            correctChoiceIndex = quiz.questionArr[index].getCorrectIndex();
            
            box.add(questionLabel);
            
            radioButtonList.clear();
            for (String option : quiz.questionArr[index].getChoices()){
                radioButtonList.add(new JRadioButton(option));
            }
            
            choiceButtonGroup = new ButtonGroup();
            for (JRadioButton radioButton : radioButtonList){
                choiceButtonGroup.add(radioButton);
                box.add(radioButton);
            } 
        }
        
        public boolean isCorrect(){
            if(radioButtonList.get(correctChoiceIndex).isSelected()){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
