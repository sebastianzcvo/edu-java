package textblocks;

// java 13 - preview
// java 14 - 2nd preview
// java 15 - standard
// string that span multiple lines obviating concatenation with escape characters for new lines
// three quotes notation requires a new line after opening quotes. That new line is not part of the string
// indentation within the string is dictated by the most left non-escape character
//   incidental indentation on the left of that character is stripped away by compiler
public class TextBlocks {
    public static void main(String[] args) {
        String textBlock = """
                <tr>
                    <td>name: %s</td>
                    <td>lastname: %s</td>
                </tr>""".formatted("name1", "lastname1");
        System.out.println(textBlock);
    }
}
