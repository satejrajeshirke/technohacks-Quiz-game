import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;
    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
      }
      public String getQuestionText() {
        return questionText;
      }
      public String[] getOptions() {
        return options;
      }
      public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
      }
      }
      class Quiz {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private Timer timer;
    private Scanner scanner;
    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.scanner = new Scanner(System.in);
       }
       public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            Question question = questions.get(currentQuestionIndex);
            askQuestion(question);
             }
           displayResults();
         }
         private void askQuestion(Question question) {
        System.out.println("\n" + question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                submitAnswer(-1); // Submit an invalid answer to indicate timeout
            }
            };
           timer = new Timer();
           timer.schedule(task, 10000); // 10 seconds timer
             int userAnswer = scanner.nextInt();
           timer.cancel(); // Cancel the timer if the user answers in time
           submitAnswer(userAnswer);
            }
            private void submitAnswer(int answerIndex) {
          Question question = questions.get(currentQuestionIndex);
          if (answerIndex == question.getCorrectAnswerIndex() + 1) {
            System.out.println("Correct!");
            score++;
          } else {
            System.out.println("Incorrect! The correct answer was: " + (question.getCorrectAnswerIndex() + 1));
          }
          }

        private void displayResults() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
          }
        }

       public class Main {
        public static void main(String[] args) {
            List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, 0));
        questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1));
        Quiz quiz = new Quiz(questions);
        quiz.start();
            }
         }
