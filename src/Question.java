import java.util.*;

class Question
{   private final String question;
    private final List<String> answers;
    private final String rightAnswer;
    private String explanation;

    public Question(String q, List<String> a)
    {   question = q;
        answers = a;

        String[] temp = a.get(0).split("---");
        rightAnswer = temp[0];
        a.set(0, rightAnswer);

        if(temp.length > 1)
            explanation = temp[1];
    }

    public String[] getAnswers()
    {   Collections.shuffle(answers);
        return answers.toArray(answers.toArray(new String[0]));
    }

    public boolean isCorrect(String answer)
    {   return rightAnswer.equals(answer);
    }

    public String getQuestion()
    {   return question;
    }

    public String getRightAnswer()
    {   return rightAnswer;
    }

    public String getExplanation()
    {   return explanation == null ? rightAnswer : explanation;
    }
}
