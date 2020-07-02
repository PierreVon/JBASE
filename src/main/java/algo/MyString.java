package algo;

public class MyString {
    public static void findTag(String content) {
        final char startChar = '@', endChar = (char) 8197;
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == startChar) {
                for (int j = i + 1; j < content.length(); j++) {
                    switch (content.charAt(j)) {
                        case startChar:
                            i = j;
                            break;
                        case endChar:
                            System.out.println(content.substring(i, j));
                            break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        char end = (char) 8197;
        findTag("@p @tag" + end + "p@tay" + end);
        findTag("");
    }
}
