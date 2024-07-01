package Controller;

public class Encryptor {

    private static final int DEFAULT_COLUMNS = 4;

    public static String encrypt(String plaintext) {

        int index = 0;
        int rows = (int) Math.ceil((double) plaintext.length() / DEFAULT_COLUMNS);
        char[][] grid = new char[rows][DEFAULT_COLUMNS];

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < DEFAULT_COLUMNS; c++) {

                if (index < plaintext.length()) {

                    grid[r][c] = plaintext.charAt(index++);

                }
                else {

                    grid[r][c] = ' ';

                }

            }

        }

        StringBuilder ciphertext = new StringBuilder();

        for (int c = 0; c < DEFAULT_COLUMNS; c++) {

            for (int r = 0; r < rows; r++) {

                ciphertext.append(grid[r][c]);

            }

        }

        return ciphertext.toString();

    }

    public static String decrypt(String ciphertext) {

        int index = 0;
        int rows = (int) Math.ceil((double) ciphertext.length() / DEFAULT_COLUMNS);
        char[][] grid = new char[rows][DEFAULT_COLUMNS];

        for (int c = 0; c < DEFAULT_COLUMNS; c++) {

            for (int r = 0; r < rows; r++) {

                grid[r][c] = ciphertext.charAt(index++);

            }

        }

        StringBuilder plaintext = new StringBuilder();

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < DEFAULT_COLUMNS; c++) {

                plaintext.append(grid[r][c]);

            }

        }

        return plaintext.toString();

    }

}
