/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package jsrv.app;

import jsrv.list.LinkedList;

import static jsrv.utilities.StringUtils.join;
import static jsrv.utilities.StringUtils.split;
import static jsrv.app.MessageUtils.getMessage;

import org.apache.commons.text.WordUtils;

public class App {
    public static void main(String[] args) {
        LinkedList tokens;
        tokens = split(getMessage());
        String result = join(tokens);
        System.out.println(WordUtils.capitalize(result));
    }
}