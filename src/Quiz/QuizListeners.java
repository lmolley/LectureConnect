package Quiz;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Quiz.*;



    public class QuizListeners implements ActionListener{
        JButton next, prev, submit;
        quizInit quizFrame;
        public QuizListeners(JButton next_in, JButton prev_in, JButton submit_in, quizInit quizFrame_in){
            next = next_in;
            prev = prev_in;
            submit = submit_in;
            quizFrame = quizFrame_in;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            JButton srcButton = (JButton)(src);
            String srcString = srcButton.getText();
            if (srcString.startsWith("Next")){
                quizFrame.advanceQuestion(1);
                quizFrame.updateQuestionPanel();
            }
            else if (srcString.startsWith("Prev")){
                quizFrame.advanceQuestion(-1);
                quizFrame.updateQuestionPanel();
            }
            else if (srcString.startsWith("Sub")){
                quizFrame.gradeQuiz();
                System.out.println(quizFrame.numCorrect +" Correct out of " + quizFrame.numQuestions);               
            }
            
            int qNUM = quizFrame.qNumber;
            System.out.println("qNumber after pressing " + srcString + " = " + qNUM);
            
        }
        
    }
    
    






















































