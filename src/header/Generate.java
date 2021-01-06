package header;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.vfs.VirtualFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;

public class Generate extends AnAction
{
    @Override
    public void actionPerformed(AnActionEvent AnActionEvent)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        VirtualFile file = AnActionEvent.getData(VIRTUAL_FILE);
        StringBuilder filename = new StringBuilder(file.getName());
        while (filename.length() < 51)
            filename.append(' ');
        StringBuilder user = new StringBuilder("By: " + System.getenv("USER") + " " + "<" + System.getenv("USER") + "@student.42lyon.fr>");
        while (user.length() < 47)
            user.append(' ');
        StringBuilder user2 = new StringBuilder("by " + System.getenv("USER"));
        while (user2.length() < 21)
            user2.append(' ');
        StringBuilder user3 = new StringBuilder("by " + System.getenv("USER"));
        while (user3.length() < 20)
            user3.append(' ');
        String startComment = "/*";
        String endComment = "*/";
        if (filename.toString().contains("Makefile"))
        {
            startComment = "#";
            endComment = "#";
        }
        String header = startComment + " " + (startComment.length() == 1 ? "*" : "") + "**************************************************************************" + (endComment.length() == 1 ? "*" : "") + " " + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                                            " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                        :::      ::::::::   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   " + filename + ":+:      :+:    :+:   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                    +:+ +:+         +:+     " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   " + user + "+#+  +:+       +#+        " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "                                                +#+#+#+#+#+   +#+           " + (endComment.length() == 1 ? ' ': "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   Created: " + dateFormat.format(date) + " " + user2 + "#+#    #+#             " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? " " : "") + "   Updated: " + dateFormat.format(date) + " " + user3 + "###   ########lyon.fr   " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + (startComment.length() == 1 ? ' ' : "") + "                                                                            " + (endComment.length() == 1 ? " ": "") + endComment + "\n" +
                startComment + " " + (startComment.length() == 1 ? "*" : "") + "**************************************************************************" + (endComment.length() == 1 ? "*" : "") + " " + endComment + "\n";

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                AnActionEvent.getData(LangDataKeys.EDITOR).getDocument().insertString(0, header);
            }
        };
        WriteCommandAction.runWriteCommandAction(getEventProject(AnActionEvent), runnable);
    }
}
