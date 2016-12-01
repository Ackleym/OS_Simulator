/**
 * Created by Michael on 11/25/2016.
 */

import java.util.Random;

public class IOBurst
{
    Random random;

    public IOBurst() {
        random = new Random();
    }
    public int generateIOBurst()
    {
        return random.nextInt(26) + 25;
    }

}
