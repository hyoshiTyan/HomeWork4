import java.util.Random;
import java.util.Scanner;
/* 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
   2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
   3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек
   4. Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
*/

  public class HomeWork4 {

      static char[][] map;
      static char USER_symbol = 'Х';
      static char PC_symbol = '0';
      static final char EMPTY = '•';
      static int SizeMap;
      static int POINTS_WIN;
      static Scanner read = new Scanner(System.in);
      static Random random = new Random();

      static void mapSize_points() {
          while (true) {
          System.out.println("Введи размер карты:");
          SizeMap = inputInteger();
          if (SizeMap > 2) {
          read.nextLine();
          break;
          } else {
                  System.out.println("Размер карты должен быть больше 2!");
              }
          }
          while (true) {
              System.out.println("Введи кол-во очков для победы: ");
              POINTS_WIN = inputInteger();
              if (POINTS_WIN > 2 && POINTS_WIN <= SizeMap) {
                  read.nextLine();
                  break;
              } else {
                  System.out.println("Кол-во очков должно быть больше 2 и меньше " + (SizeMap + 1));
              }
          }
      }

        static int inputInteger ()
        {
            while (true)
            {
                if (read.hasNextInt())
                {
                    return
                    read.nextInt();
                } else {
                    System.out.println("Неверное число.");
                    read.nextLine();
                }
            }
        }

        static void initMap ()
        {
            map = new char[SizeMap][SizeMap];
            for (int i = 0; i < map.length; i++)
            {
                for (int j = 0; j < map.length; j++)
                {
                    map[i][j] = EMPTY;
                }
            }
        }

        static void printMap ()
        {
            System.out.print("  ");
            for (int i = 1; i <= map.length; i++)
            {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 1; i <= map.length; i++)
            {
                System.out.print(i + " ");
                for (char symbol : map[i - 1])
                {
                    System.out.print(symbol + " ");
                }
                System.out.println();
            }
        }

        static boolean isEmptyAndExist (int x, int y)
        {
            if (x >= 0 && x < map.length && y >= 0 && y < map.length && map[x][y] == EMPTY)
            {
                return true;
            } else {
                System.out.println("Неверный ход!");
                return false;
            }
        }

        static void USER_turn()
        {
            int x, y;
            do
            { System.out.println("X:");
                x = inputInteger() - 1;
                read.nextLine();
                y = inputInteger() - 1 ;
                read.nextLine();
            } while (!(isEmptyAndExist(x, y)));
              map[x][y] = USER_symbol;
        }

        static void PC_turn()
        {
            int x, y;
            do
            {
                x = random.nextInt(map.length);
                y = random.nextInt(map.length);
            } while (!isEmptyAndExist(x, y));
            map[x][y] = PC_symbol;
            System.out.println("Ход компьютера:");
        }


        static boolean isVictory (char symbol)
        {
        int diagonalPoints1 = 0, diagonalPoints2 = 0, verticalPoints, horizontalPoints;
        for (int i = 0; i < map.length; i++)
        {
        verticalPoints = 0;
        horizontalPoints = 0;
                if (map[i][i] == symbol)
                {
                    diagonalPoints1 += 1;
                } else {
                    diagonalPoints1 = 0;
                }
                if (map[i][map.length - 1 - i] == symbol)
                {
                    diagonalPoints2 += 1;
                } else {
                    diagonalPoints2 = 0;
                }
                if (diagonalPoints1 == POINTS_WIN || diagonalPoints2 == POINTS_WIN)
                {
                        return true;
                }
                for (int j = 0; j < map.length; j++)
                {
                    if (map[i][j] == symbol)
                    {
                        horizontalPoints += 1;
                    } else {
                        horizontalPoints = 0;
                    }
                    if (map[j][i] == symbol)
                    {
                        verticalPoints += 1;
                    } else {
                        verticalPoints = 0;
                    }
                    if (horizontalPoints == POINTS_WIN || verticalPoints == POINTS_WIN)
                    {
                        return true;
                    }
                }
            }
                        return false;
        }

        static boolean isFullMap ()
        {
            for (int i = 0; i < map.length; i++)
            {
                for (char value : map[i])
                {
                    if (value == EMPTY)
                    {
                        return false;
                    }
                }
            }
                        return true;
        }


        public static void main (String[]args) {
            System.out.println("Игра *Крестики-нолики* началась!");
            mapSize_points();
            initMap();
            printMap();
            while (true)
            {
                USER_turn();
                printMap();
                if (isVictory(USER_symbol))
                {
                    System.out.println("Ты победил!");
                    break;
                }
                PC_turn();
                printMap();
                if (isVictory(PC_symbol))
                {
                    System.out.println("Ты проиграл!");
                    break;
                }
                if (isFullMap())
                {
                    System.out.println("Ничья!");
                    break;
                }
            }
        }
    }


