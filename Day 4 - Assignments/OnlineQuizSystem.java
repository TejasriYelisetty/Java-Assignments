import java.util.*;

class Question {
    String questionText;
    String[] options;
    String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer.trim().toLowerCase();
    }

    public boolean checkAnswer(int answerIndex) {
        String selectedAnswer = options[answerIndex - 1].toLowerCase();  
        return correctAnswer.equals(selectedAnswer);
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

class Quiz {
    List<Question> questions;
    Map<Question, Integer> userAnswers;

    public Quiz() {
        this.questions = new ArrayList<>();
        this.userAnswers = new HashMap<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            question.displayQuestion();
            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            userAnswers.put(question, answer);
        }
    }

    public int calculateScore() {
        int score = 0;
        for (Map.Entry<Question, Integer> entry : userAnswers.entrySet()) {
            if (entry.getKey().checkAnswer(entry.getValue())) {
                score++;
            }
        }
        return score;
    }
}

class User {
    String username;
    int score;

    public User(String username) {
        this.username = username;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void displayScore() {
        System.out.println(username + "'s score: " + score);
    }
}

public class OnlineQuizSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = new User(username);

        Quiz quiz = new Quiz();

        System.out.println("Let's add questions to the quiz!");
        System.out.print("How many questions do you want to add? ");
        int numQuestions = scanner.nextInt();
        scanner.nextLine();  // To consume the newline character

        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter details for question " + (i + 1));

            System.out.print("Question Text: ");
            String questionText = scanner.nextLine();

            String[] options = new String[4];
            for (int j = 0; j < 4; j++) {
                System.out.print("Option " + (j + 1) + ": ");
                options[j] = scanner.nextLine();
            }

            System.out.print("Enter the number of the correct answer (1-4): ");
            int correctAnswerIndex = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            String correctAnswer = options[correctAnswerIndex - 1];

            Question question = new Question(questionText, options, correctAnswer);
            quiz.addQuestion(question);
        }

        quiz.takeQuiz();

        int score = quiz.calculateScore();
        user.setScore(score);

        user.displayScore();

        scanner.close();
    }
}
