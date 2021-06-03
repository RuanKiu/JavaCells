public class Engine 
{
  private boolean[][] map;
  private double populationFactor;
  public Engine(int w, int h)
  {
    map = new boolean[h][w];
    populationFactor = 0.9;
  }
  public void setAlive(int x, int y)
  {
    map[y][x] = true;
  }
  public void calculateFrame()
  {
    for (int r = 0; r < map.length; r++)
    {
      for (int c = 0; c < map[r].length; c++)
      {
        int count = 0;
        for (int i = r - 1; i >= 0 && i < r + 2 && i < map.length; i++)
        {
          for (int j = c - 1; j >= 0 && j < c + 2 && j < map[r].length; j++)
          {
            if (i != r && j != c && map[i][j])
            {
              count++; 
            }
          }
        }
        if (count >= 4 || count <= 1)
        {
          map[r][c] = false;
        }
        else 
        {
          map[r][c] = true;
        }
      }
    }
  }
  public boolean[][] getMap()
  {
    return map;
  }
  public void resize(int w, int h)
  {
    boolean[][] empty = new boolean[h][w];
    for (int r = 0; r < h; r++)
    {
      for (int c = 0; c < w; c++)
      {
        if (r < map.length && c < map[r].length)
        {
          empty[r][c] = map[r][c];
        }
        else
        {
          empty[r][c] = (Math.random() > populationFactor);
        }
      }
    }
    map = empty;
  }
  public void randomize()
  {
    for (int r = 0; r < map.length; r++)
    {
      for (int c = 0; c < map[r].length; c++)
      {
        map[r][c] = (Math.random() > populationFactor);
      }
    }
  }
  public String toString()
  {
    String output = "";
    for (int r = 0; r < map.length; r++)
    {
      for (int c = 0; c < map[r].length; c++)
      {
        output+=map[r][c] + " ";
      }
      output+="\n";
    }
    return output;
  }
}

