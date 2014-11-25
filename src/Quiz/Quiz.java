package Quiz;

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Quiz{
    
    public class Question{
        String ques;
        String[] choices;
        int correctIndex;
        
        Question(String q,
                 String[] choices_in,
                 int correct_in){
            ques = q;
            correctIndex = correct_in;
            choices = new String[choices_in.length];
            for (int i = 0; i < choices_in.length; i++){
                choices[i] = choices_in[i];
            }
        }
        public String getQ(){
            return ques;
        }
        public String[] getChoices(){
            return choices;
        }
        public int getCorrectIndex(){
            return correctIndex;
        }
    }
    
    
    File myFile;
    JFrame mainFrame;
    List<Question> questionList;
    Question[] questionArr;
    int qArrIndex = 0;
    Scanner fileScanner = null;
    Scanner stringScanner;
    String fileLine = "";
    Question nextQuestion;
    String content;
    
    public Quiz(JDialog mainFrame){
        String s = loadQuizFile();
        
        fileScanner = new Scanner(s);
        prepareQuiz();
        questionArr = new Question[questionList.size()];
        questionArr = questionList.toArray(questionArr);
    }
    
    public Quiz(String entireFile){
        fileScanner = new Scanner(entireFile);
        prepareQuiz();
        questionArr = new Question[questionList.size()];
        questionArr = questionList.toArray(questionArr);
    }
    
    private String loadQuizFile(){
        content = "No quiz loaded!";
        JFileChooser loadChooser = new JFileChooser("Load Quiz File");
        loadChooser.setMultiSelectionEnabled(false);
        int approve = loadChooser.showOpenDialog(mainFrame);
        if (approve == JFileChooser.APPROVE_OPTION){
            myFile = loadChooser.getSelectedFile();
            try {
                fileScanner = new Scanner(myFile);
                content = fileScanner.useDelimiter("\\Z").next();
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File ptr is null");
            }
           
        }
        return content; 
    }
    
    private void prepareQuiz(){
        boolean preparing = true;
        questionList = new ArrayList<Question>();
        try{
            while (preparing){
                try{
                    preparing = loadQuestion();
                    questionList.add(nextQuestion);
                    qArrIndex++;
                }
                catch(Exception e){//outputmismatch
                    System.out.print(e.getMessage());
                }
            }
        }
        catch(Exception e){//filenotfound
            System.out.print(e.getMessage());
        }
    }
    
    private boolean loadQuestion(){
        if (!fileScanner.hasNext()){
            return false;
        }
        
        while(!fileLine.startsWith("@")){
            fileLine = fileScanner.nextLine();
        }
        
        stringScanner = new Scanner(fileLine);
        stringScanner.next();
        String q = stringScanner.nextLine();
        
        List<String> answers = new ArrayList<String>();
        int answerIndex = 0;
        int correctIndex = -1;
        fileLine = "";
        
        while(!fileLine.startsWith("@")){
            if (fileScanner.hasNext()){
                fileLine = fileScanner.nextLine();
                
                if (fileLine.startsWith("$")){
                    stringScanner = new Scanner(fileLine);
                    stringScanner.next();
                    fileLine = stringScanner.nextLine();
                    correctIndex = answerIndex;
                }
               
                else if(fileLine.startsWith("@")){
                    break;
                }
                
                answers.add(fileLine);
                answerIndex++;
            
            }
            
            else{
                String[] answArr = new String[(answers.size())];
                answArr = answers.toArray(answArr);
                nextQuestion = new Question(q, answArr, correctIndex);
                return false;
            }
        }
        
        String[] answArr = new String[(answers.size())];
        answArr = answers.toArray(answArr);
        nextQuestion = new Question(q, answArr, correctIndex);
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}