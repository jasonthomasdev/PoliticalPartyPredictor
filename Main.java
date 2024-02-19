import java.util.*;
import java.io.*;

public class Main {
    // Define the Party class with attributes for the name and score
    static class Party {
        String name;
        int score;

        // Constructor initializes the name and score
        public Party(String name) {
            this.name = name;
            this.score = 0;
        }
    }

    // Define the User class with attributes for a map of parties and a list of answers
    static class User {
        Map<String, Party> parties = new HashMap<>();
        List<String> answers = new ArrayList<>();

        // Constructor initializes the map of parties
        public User() {
            parties.put("A", new Party("Democratic"));
            parties.put("B", new Party("Republican"));
            parties.put("C", new Party("Green"));
            parties.put("D", new Party("Libertarian"));
        }

        // Add answer to the list of answers
        public void addAnswer(String answer) {
            this.answers.add(answer.toUpperCase());  // convert to upper case
        }

        // Calculate the score for each party based on the answers
        public void calculateScore() {
            for (String answer : this.answers) {
                if (answer.equals("A")) {
                    parties.get("A").score += 2;
                    parties.get("B").score++;
                } else if (answer.equals("B")) {
                    parties.get("B").score += 2;
                    parties.get("C").score++;
                } else if (answer.equals("C")) {
                    parties.get("C").score += 2;
                    parties.get("D").score++;
                } else {
                    parties.get("D").score += 2;
                }
            }
        }

        // Predict the political party based on the highest score
        public String predictParty() {
            int maxScore = -1;
            String party = "";
            for (Map.Entry<String, Party> entry : parties.entrySet()) {
                if (entry.getValue().score > maxScore) {
                    maxScore = entry.getValue().score;
                    party = entry.getValue().name;
                }
            }
            return party;
        }

        // Save the score for each party in separate files
        public void saveData() {
            for (Map.Entry<String, Party> entry : parties.entrySet()) {
                try {
                    PrintWriter writer = new PrintWriter(new File(entry.getValue().name + "_data.txt"));
                    writer.println(entry.getValue().score);
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        // Load the score for each party from separate files, or create the files if they don't exist
        public void loadData() {
            for (Map.Entry<String, Party> entry : parties.entrySet()) {
                File file = new File(entry.getValue().name + "_data.txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Scanner reader = new Scanner(file);
                        if (reader.hasNext()) {
                            entry.getValue().score = Integer.parseInt(reader.nextLine());
                        }
                        reader.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a new user and load the previously stored data
        User user = new User();
        user.loadData();

        Scanner in = new Scanner(System.in);
      
        // Question 1
        System.out.println("What should the government do to help the poor?");
        System.out.println("A. Make it easier to apply for assistance.");
        System.out.println("B. Allow parents to use education funds for charter schools.");
        System.out.println("C. Create welfare to work programs.");
        System.out.println("D. Nothing.");
        String answer = in.nextLine();
        user.addAnswer(answer);

        // Question 2
        System.out.println("What is your stance on gun ownership?");
        System.out.println("A. Strict regulation or ban.");
        System.out.println("B. No restrictions on gun ownership.");
        System.out.println("C. Some level of regulation is necessary, but individuals have the right to own guns.");
        System.out.println("D. I have no opinion on gun control.");
        answer = in.nextLine();
        user.addAnswer(answer);

        // Question 3
        System.out.println("What are your views on climate change?");
        System.out.println("A. Climate change is a critical issue and must be addressed immediately.");
        System.out.println("B. Climate change is not as serious as it is made out to be.");
        System.out.println("C. We should take some measures to address climate change but not at the expense of the economy.");
        System.out.println("D. I don't believe in climate change.");
        answer = in.nextLine();
        user.addAnswer(answer);

        // Question 4
        System.out.println("How should the government address education?");
        System.out.println("A. Education should be free for everyone.");
        System.out.println("B. Education should be privatized.");
        System.out.println("C. Some level of government involvement is necessary, but schools need more autonomy.");
        System.out.println("D. The government should not be involved in education.");
        answer = in.nextLine();
        user.addAnswer(answer);

        // Question 5
        System.out.println("What is your stance on immigration?");
        System.out.println("A. Open borders and accept more immigrants.");
        System.out.println("B. Stricter regulations on immigration.");
        System.out.println("C. A balance of regulations and openness.");
        System.out.println("D. Complete closure of borders.");
        answer = in.nextLine();
        user.addAnswer(answer);

        // Calculate score and predict political party based on user answers
        user.calculateScore();
        String predictedParty = user.predictParty();
        System.out.println("Predicted party is " + predictedParty);

        // Final question
        System.out.println("What political party do you affiliate with?");
        System.out.println("A. Democratic");
        System.out.println("B. Republican");
        System.out.println("C. Green");
        System.out.println("D. Libertarian");
        answer = in.nextLine();
        String userParty = user.parties.get(answer.toUpperCase()).name;  // convert to upper case
        System.out.println("Your party is " + userParty + ".");

        // Compare the given answer with the predicted answer
        System.out.println("The predicted party is " + predictedParty + ".");
        if (userParty.equals(predictedParty)) {
            System.out.println("The prediction was correct!");
        }
        else {
            System.out.println("The prediction was incorrect.");
        }

        // Save data for future user sessions
        user.saveData(); 
    }
}