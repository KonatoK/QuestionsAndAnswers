Questions & Answers
----------------------

Someone asked me to make an app for questions so it could help them study, it looks kinda useful, but
I guess it lacks features for an in-depth study application, either way it doesn't mean it's useless.

You can add your own questions under the `questions/` directory, just make a directory with the name
of the subject you want and add some text files in there with your questions and answers, the name of
the file doesn't matter, as long as it can be read as UTF8 text it should be fine.

A question file looks like this:

```
∫(du + dv - dw)
∫du + ∫dv − ∫dw---Integrals can be split in different components without altering them
∫du + ∫dv + ∫dw
∫du - ∫dv − ∫dw
∫du dv dw
```

The first line is the question, the second is the right answer (you can add an explanation by putting
three dashes `---` and then whatever you want to be displayed when they click the right answer), and
the rest of the lines are extra wrong answers, the program will consider a valid questions as long as
the file has 3 lines (question, right answer and 1 wrong answer), but you can add as many wrong answers
as you want, answers are shuffled when displayed in the application so don't worry about right answer
being always the first.

There are also piano sounds if you guess right or wrong, if they bother you just delete the sound files,
the program won't fail, if you just want to delete the one that plays when you're wrong, delete `00.wav`.

You can add as many sets of questions and questions to the program (as long as your filesystem allows
you...), you will get asked at most 50 questions per session though, so keep that in mind.

Since `v0.6` it's possible to add images to a question, images need to be placed inside the directory
with the questions inside a `images/` directory, images **must match the file name of the question**
including file extension (this is, if you have a `questions/Integrals/exponential.txt` your image
will be `questions/Integrals/images/exponential.txt`), they will resize to their original size so
it's not recommended to use large images, use with caution.

Adding images is entirely optional, if you want to remove the text of a question and just leave the
image then leave the first line of the question file blank.
