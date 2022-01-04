package studio.mashkarik.microtankwars2d.maps;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import studio.mashkarik.microtankwars2d.actor.Flag;
import studio.mashkarik.microtankwars2d.actor.Grid;
import studio.mashkarik.microtankwars2d.actor.Tank;
import studio.mashkarik.microtankwars2d.actor.block.Bush;
import studio.mashkarik.microtankwars2d.actor.block.Respawn;
import studio.mashkarik.microtankwars2d.actor.block.Rock;
import studio.mashkarik.microtankwars2d.actor.block.Wood;
import studio.mashkarik.microtankwars2d.ai.Lee;
import studio.mashkarik.microtankwars2d.screen.GameScreen;

public class Parser {

    public static int RED_FLAG_POSITION;
    public static int BLUE_FLAG_POSITION;
    private static Grid grid;
    private final ArrayList<Rock> blockRocks = new ArrayList<>();
    private final ArrayList<Wood> blockWoods = new ArrayList<>();
    private final ArrayList<Bush> blockGreens = new ArrayList<>();
    private final int[][] map;
    private final int[][] mapMatrix =
            {
                    {113, 118, 123, 128, 133, 138, 143, 148, 153, 158, 163, 168, 173, 178, 183, 188, 193, 198, 203, 208, 213, 218,},
                    {673, 678, 683, 688, 693, 698, 703, 708, 713, 718, 723, 728, 733, 738, 743, 748, 753, 758, 763, 768, 773, 778,},
                    {1233, 1238, 1243, 1248, 1253, 1258, 1263, 1268, 1273, 1278, 1283, 1288, 1293, 1298, 1303, 1308, 1313, 1318, 1323, 1328, 1333, 1338,},
                    {1793, 1798, 1803, 1808, 1813, 1818, 1823, 1828, 1833, 1838, 1843, 1848, 1853, 1858, 1863, 1868, 1873, 1878, 1883, 1888, 1893, 1898,},
                    {2353, 2358, 2363, 2368, 2373, 2378, 2383, 2388, 2393, 2398, 2403, 2408, 2413, 2418, 2423, 2428, 2433, 2438, 2443, 2448, 2453, 2458,},
                    {2913, 2918, 2923, 2928, 2933, 2938, 2943, 2948, 2953, 2958, 2963, 2968, 2973, 2978, 2983, 2988, 2993, 2998, 3003, 3008, 3013, 3018,},
                    {3473, 3478, 3483, 3488, 3493, 3498, 3503, 3508, 3513, 3518, 3523, 3528, 3533, 3538, 3543, 3548, 3553, 3558, 3563, 3568, 3573, 3578,},
                    {4033, 4038, 4043, 4048, 4053, 4058, 4063, 4068, 4073, 4078, 4083, 4088, 4093, 4098, 4103, 4108, 4113, 4118, 4123, 4128, 4133, 4138,},
                    {4593, 4598, 4603, 4608, 4613, 4618, 4623, 4628, 4633, 4638, 4643, 4648, 4653, 4658, 4663, 4668, 4673, 4678, 4683, 4688, 4693, 4698,},
                    {5153, 5158, 5163, 5168, 5173, 5178, 5183, 5188, 5193, 5198, 5203, 5208, 5213, 5218, 5223, 5228, 5233, 5238, 5243, 5248, 5253, 5258,},
                    {5713, 5718, 5723, 5728, 5733, 5738, 5743, 5748, 5753, 5758, 5763, 5768, 5773, 5778, 5783, 5788, 5793, 5798, 5803, 5808, 5813, 5818,},
                    {6273, 6278, 6283, 6288, 6293, 6298, 6303, 6308, 6313, 6318, 6323, 6328, 6333, 6338, 6343, 6348, 6353, 6358, 6363, 6368, 6373, 6378,},
                    {6833, 6838, 6843, 6848, 6853, 6858, 6863, 6868, 6873, 6878, 6883, 6888, 6893, 6898, 6903, 6908, 6913, 6918, 6923, 6928, 6933, 6938,},
                    {7393, 7398, 7403, 7408, 7413, 7418, 7423, 7428, 7433, 7438, 7443, 7448, 7453, 7458, 7463, 7468, 7473, 7478, 7483, 7488, 7493, 7498,},
                    {7953, 7958, 7963, 7968, 7973, 7978, 7983, 7988, 7993, 7998, 8003, 8008, 8013, 8018, 8023, 8028, 8033, 8038, 8043, 8048, 8053, 8058,},
                    {8513, 8518, 8523, 8528, 8533, 8538, 8543, 8548, 8553, 8558, 8563, 8568, 8573, 8578, 8583, 8588, 8593, 8598, 8603, 8608, 8613, 8618,},
                    {9073, 9078, 9083, 9088, 9093, 9098, 9103, 9108, 9113, 9118, 9123, 9128, 9133, 9138, 9143, 9148, 9153, 9158, 9163, 9168, 9173, 9178,},
                    {9633, 9638, 9643, 9648, 9653, 9658, 9663, 9668, 9673, 9678, 9683, 9688, 9693, 9698, 9703, 9708, 9713, 9718, 91123, 91128, 9733, 9738,},
                    {10193, 10198, 10203, 10208, 10213, 10218, 10223, 10228, 10233, 10238, 10243, 10248, 10253, 10258, 10263, 10268, 10273, 10278, 10283, 10288, 10293, 10298,},
                    {10753, 10758, 10763, 10768, 10773, 10778, 10783, 10788, 10793, 10798, 10803, 10808, 10813, 10818, 10823, 10828, 10833, 10838, 10843, 10848, 10853, 10858,},
                    {11313, 11318, 11323, 11328, 11333, 11338, 11343, 11348, 11353, 11358, 11363, 11368, 11373, 11378, 11383, 11388, 11393, 11398, 11403, 11408, 11413, 11418,},
                    {11873, 11878, 11883, 11888, 11893, 11898, 11903, 11908, 11913, 11918, 11923, 11928, 11933, 11938, 11943, 11948, 11953, 11958, 11963, 11968, 11973, 11978,},
            };
    private Color ctr1, ctr2, ctr3, ctr4, ctr5;
    private Color ctb1, ctb2, ctb3, ctb4, ctb5;
    private Tank tankRed4;
    private Tank tankRed1;
    private Tank tankRed2;
    private Tank tankRed3;
    private Tank tankRed5;
    private int tru, tr1, tr2, tr3, tr4;
    private Tank tankBlue1;
    private Tank tankBlue2;
    private Tank tankBlue3;
    private Tank tankBlue4;
    private Tank tankBlue5;
    private int tbu, tb1, tb2, tb3, tb4;

    private Respawn blockRespawn;
    private Respawn blockRespawn1;
    private Respawn blockRespawn2;
    private Respawn blockRespawn3;
    private Respawn blockRespawn4;

    private Respawn blockRespawn5;
    private Respawn blockRespawn6;
    private Respawn blockRespawn7;
    private Respawn blockRespawn8;
    private Respawn blockRespawn9;

    private Flag flagBlue;
    private Flag flagRed;
    private int bf1, bf2;
    private Lee lee;

    public Parser(Grid grid, int[][] map) {
        Parser.grid = grid;
        this.map = map;
        initField();
        mapInit();
    }

    private void initField() {
        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix.length; j++) {
                //-----------flag--------------
                if (map[i][j] == 100) {
                    bf1 = mapMatrix[i][j];
                    if (GameScreen.TEAM_TYPE == 1)
                        RED_FLAG_POSITION = mapMatrix[i][j];
                    else BLUE_FLAG_POSITION = mapMatrix[i][j];
                }
                if (map[i][j] == 200) {
                    bf2 = mapMatrix[i][j];
                    if (GameScreen.TEAM_TYPE == 1)
                        BLUE_FLAG_POSITION = mapMatrix[i][j];
                    else RED_FLAG_POSITION = mapMatrix[i][j];
                }
                //-----------tank------------
                if (map[i][j] == 10) {
                    tbu = mapMatrix[i][j];
                }
                if (map[i][j] == 20) {
                    tb1 = mapMatrix[i][j];
                }
                if (map[i][j] == 30) {
                    tb2 = mapMatrix[i][j];
                }
                if (map[i][j] == 40) {
                    tb3 = mapMatrix[i][j];
                }

                if (map[i][j] == 45) {
                    tb4 = mapMatrix[i][j];
                }

                if (map[i][j] == 50) {
                    tru = mapMatrix[i][j];
                }
                if (map[i][j] == 60) {
                    tr1 = mapMatrix[i][j];
                }
                if (map[i][j] == 70) {
                    tr2 = mapMatrix[i][j];
                }
                if (map[i][j] == 80) {
                    tr3 = mapMatrix[i][j];
                }

                if (map[i][j] == 85) {
                    tr4 = mapMatrix[i][j];
                }
                //-----------green-----------
                if (map[i][j] == 4) {
                    blockGreens.add(new Bush(grid, mapMatrix[i][j], GameScreen.BUSH_COLOR));
                }
                //--------------ice--------------


                //-----------rock---------------
                if (map[i][j] == 1) {
                    blockRocks.add(new Rock(grid, mapMatrix[i][j]
                            , new Color(GameScreen.BRICK_COLOR)));
                }

                //-----------wood---------------
                if (map[i][j] == 2) {
                    blockWoods.add(new Wood(grid, mapMatrix[i][j], GameScreen.WOOD_COLOR));
                }
            }
        }
    }

    private void mapInit() {

        lee = new Lee(grid);

        blockRespawn = new Respawn(grid, tru, "left");
        blockRespawn1 = new Respawn(grid, tr1, "left");
        blockRespawn2 = new Respawn(grid, tr2, "down");
        blockRespawn3 = new Respawn(grid, tr3, "down");
        blockRespawn9 = new Respawn(grid, tr4, "down");

        blockRespawn4 = new Respawn(grid, tbu, "right");
        blockRespawn5 = new Respawn(grid, tb1, "right");
        blockRespawn6 = new Respawn(grid, tb2, "upb");
        blockRespawn7 = new Respawn(grid, tb3, "upb");
        blockRespawn8 = new Respawn(grid, tb4, "down");

        if (GameScreen.TEAM_TYPE == 0) {

            flagBlue = new Flag(grid, bf1
                    , new Color(GameScreen.FLAG_BLUE));

            flagRed = new Flag(grid, bf2
                    , new Color(GameScreen.FLAG_RED));

            Random random = new Random();

            int rnd = random.nextInt(5);

            ArrayList<Color> arrayList = new ArrayList<>();
            arrayList.add(GameScreen.COLORRED);
            arrayList.add(GameScreen.COLORRED);
            arrayList.add(GameScreen.COLORRED);
            arrayList.add(GameScreen.COLORRED);
            arrayList.add(GameScreen.COLORRED);

            arrayList.set(rnd, GameScreen.USER_TANK_COLOR_RED);

            ctr1 = arrayList.get(0);
            ctr2 = arrayList.get(1);
            ctr3 = arrayList.get(2);
            ctr4 = arrayList.get(3);
            ctr5 = arrayList.get(4);

            ctb1 = GameScreen.COLORBLUE;
            ctb2 = GameScreen.COLORBLUE;
            ctb3 = GameScreen.COLORBLUE;
            ctb4 = GameScreen.COLORBLUE;
            ctb5 = GameScreen.COLORBLUE;

            tankRed4 = new Tank(tbu, "left", ctr1, grid, lee);
            tankRed1 = new Tank(tb1, "left", ctr2, grid, lee);
            tankRed2 = new Tank(tb2, "down", ctr3, grid, lee);
            tankRed3 = new Tank(tb3, "down", ctr4, grid, lee);
            tankRed5 = new Tank(tb4, "down", ctr5, grid, lee);

            tankBlue1 = new Tank(tru, "right", ctb1, grid, lee);
            tankBlue2 = new Tank(tr1, "right", ctb2, grid, lee);
            tankBlue3 = new Tank(tr2, "upb", ctb3, grid, lee);
            tankBlue4 = new Tank(tr3, "upb", ctb4, grid, lee);
            tankBlue5 = new Tank(tr4, "down", ctb5, grid, lee);
        }
        if (GameScreen.TEAM_TYPE == 1) {

            flagBlue = new Flag(grid, bf2
                    , new Color(GameScreen.FLAG_BLUE));

            flagRed = new Flag(grid, bf1
                    , new Color(GameScreen.FLAG_RED));

            Random random = new Random();

            int rnd = random.nextInt(5);

            ArrayList<Color> arrayList = new ArrayList<>();
            arrayList.add(GameScreen.COLORBLUE);
            arrayList.add(GameScreen.COLORBLUE);
            arrayList.add(GameScreen.COLORBLUE);
            arrayList.add(GameScreen.COLORBLUE);
            arrayList.add(GameScreen.COLORBLUE);

            arrayList.set(rnd, GameScreen.USER_TANK_COLOR_BLUE);

            ctb1 = arrayList.get(0);
            ctb2 = arrayList.get(1);
            ctb3 = arrayList.get(2);
            ctb4 = arrayList.get(3);
            ctb5 = arrayList.get(4);

            ctr1 = GameScreen.COLORRED;
            ctr2 = GameScreen.COLORRED;
            ctr3 = GameScreen.COLORRED;
            ctr4 = GameScreen.COLORRED;
            ctr5 = GameScreen.COLORRED;

            tankRed4 = new Tank(tru, "left", ctr1, grid, lee);
            tankRed1 = new Tank(tr1, "left", ctr2, grid, lee);
            tankRed2 = new Tank(tr2, "down", ctr3, grid, lee);
            tankRed3 = new Tank(tr3, "down", ctr4, grid, lee);
            tankRed4 = new Tank(tr4, "down", ctr5, grid, lee);

            tankBlue1 = new Tank(tbu, "right", ctb1, grid, lee);
            tankBlue2 = new Tank(tb1, "right", ctb2, grid, lee);
            tankBlue3 = new Tank(tb2, "upb", ctb3, grid, lee);
            tankBlue4 = new Tank(tb3, "upb", ctb4, grid, lee);
            tankBlue5 = new Tank(tb4, "upb", ctb5, grid, lee);
        }
    }

    public void mapRender(float v) {

        flagBlue.render(v);
        flagRed.render(v);

        for (Rock blockRock : blockRocks)
            blockRock.render(v);

        for (Bush blockGreen : blockGreens)
            blockGreen.render(v);

        blockRespawn.render(v);
        blockRespawn1.render(v);
        blockRespawn2.render(v);
        blockRespawn3.render(v);
        blockRespawn4.render(v);
        blockRespawn5.render(v);
        blockRespawn6.render(v);
        blockRespawn7.render(v);
        blockRespawn8.render(v);
        blockRespawn9.render(v);

        if (GameScreen.TEAM_TYPE == 1) {
            if (tankBlue1 != null)
                if (tankBlue1.getDispose() && tankBlue1.getBullet() == null) {
                    tankBlue1.clear();
                    if (tankBlue1.getBullet() == null)
                        tankBlue1 = new Tank(tbu, "right", ctb1, grid, lee);
                } else {
                    tankBlue1.render(v);
                }
            if (tankBlue2 != null)
                if (tankBlue2.getDispose() && tankBlue2.getBullet() == null) {
                    tankBlue2.clear();
                    if (tankBlue2.getBullet() == null)
                        tankBlue2 = new Tank(tb1, "right", ctb2, grid, lee);
                } else {
                    tankBlue2.render(v);
                }
            if (tankBlue3 != null)
                if (tankBlue3.getDispose() && tankBlue3.getBullet() == null) {
                    tankBlue3.clear();
                    if (tankBlue3.getBullet() == null)
                        tankBlue3 = new Tank(tb2, "upb", ctb3, grid, lee);
                } else {
                    tankBlue3.render(v);
                }
            if (tankBlue4 != null)
                if (tankBlue4.getDispose() && tankBlue4.getBullet() == null) {
                    tankBlue4.clear();
                    if (tankBlue4.getBullet() == null)
                        tankBlue4 = new Tank(tb3, "upb", ctb4, grid, lee);
                } else {
                    tankBlue4.render(v);
                }

            if (tankBlue5 != null)
                if (tankBlue5.getDispose() && tankBlue5.getBullet() == null) {
                    tankBlue5.clear();
                    if (tankBlue5.getBullet() == null)
                        tankBlue5 = new Tank(tb4, "upb", ctb5, grid, lee);
                } else {
                    tankBlue5.render(v);
                }


            if (tankRed1 != null)
                if (tankRed1.getDispose() && tankRed1.getBullet() == null) {
                    tankRed1.clear();
                    if (tankRed1.getBullet() == null)
                        tankRed1 = new Tank(tr1, "left", ctr2, grid, lee);
                } else {
                    tankRed1.render(v);
                }
            if (tankRed2 != null)
                if (tankRed2.getDispose() && tankRed2.getBullet() == null) {
                    tankRed2.clear();
                    if (tankRed2.getBullet() == null)
                        tankRed2 = new Tank(tr2, "down", ctr3, grid, lee);
                } else {
                    tankRed2.render(v);
                }
            if (tankRed3 != null)
                if (tankRed3.getDispose() && tankRed3.getBullet() == null) {
                    tankRed3.clear();
                    if (tankRed3.getBullet() == null)
                        tankRed3 = new Tank(tr3, "down", ctr4, grid, lee);
                } else {
                    tankRed3.render(v);
                }

            if (tankRed4 != null)
                if (tankRed4.getDispose() && tankRed4.getBullet() == null) {
                    tankRed4.clear();
                    if (tankRed4.getBullet() == null)
                        tankRed4 = new Tank(tru, "left", ctr1, grid, lee);
                } else {
                    tankRed4.render(v);
                }
            if (tankRed5 != null)
                if (tankRed5.getDispose() && tankRed5.getBullet() == null) {
                    tankRed5.clear();
                    if (tankRed5.getBullet() == null)
                        tankRed5 = new Tank(tr4, "left", ctr5, grid, lee);
                } else {
                    tankRed5.render(v);
                }
        }
        if (GameScreen.TEAM_TYPE == 0) {
            if (tankBlue1 != null)
                if (tankBlue1.getDispose() && tankBlue1.getBullet() == null) {
                    tankBlue1.clear();
                    if (tankBlue1.getBullet() == null)
                        tankBlue1 = new Tank(tru, "right", ctb1, grid, lee);
                } else {
                    tankBlue1.render(v);
                }
            if (tankBlue2 != null)
                if (tankBlue2.getDispose() && tankBlue2.getBullet() == null) {
                    tankBlue2.clear();
                    if (tankBlue2.getBullet() == null)
                        tankBlue2 = new Tank(tr1, "right", ctb2, grid, lee);
                } else {
                    tankBlue2.render(v);
                }
            if (tankBlue3 != null)
                if (tankBlue3.getDispose() && tankBlue3.getBullet() == null) {
                    tankBlue3.clear();
                    if (tankBlue3.getBullet() == null)
                        tankBlue3 = new Tank(tr2, "upb", ctb3, grid, lee);
                } else {
                    tankBlue3.render(v);
                }
            if (tankBlue4 != null)
                if (tankBlue4.getDispose() && tankBlue4.getBullet() == null) {
                    tankBlue4.clear();
                    if (tankBlue4.getBullet() == null)
                        tankBlue4 = new Tank(tr3, "upb", ctb4, grid, lee);
                } else {
                    tankBlue4.render(v);
                }

            if (tankBlue5 != null)
                if (tankBlue5.getDispose() && tankBlue5.getBullet() == null) {
                    tankBlue5.clear();
                    if (tankBlue5.getBullet() == null)
                        tankBlue5 = new Tank(tr4, "upb", ctb5, grid, lee);
                } else {
                    tankBlue5.render(v);
                }


            if (tankRed1 != null)
                if (tankRed1.getDispose() && tankRed1.getBullet() == null) {
                    tankRed1.clear();
                    if (tankRed1.getBullet() == null)
                        tankRed1 = new Tank(tb1, "left", ctr2, grid, lee);
                } else {
                    tankRed1.render(v);
                }
            if (tankRed2 != null)
                if (tankRed2.getDispose() && tankRed2.getBullet() == null) {
                    tankRed2.clear();
                    if (tankRed2.getBullet() == null)
                        tankRed2 = new Tank(tb2, "down", ctr3, grid, lee);
                } else {
                    tankRed2.render(v);
                }
            if (tankRed3 != null)
                if (tankRed3.getDispose() && tankRed3.getBullet() == null) {
                    tankRed3.clear();
                    if (tankRed3.getBullet() == null)
                        tankRed3 = new Tank(tb3, "down", ctr4, grid, lee);
                } else {
                    tankRed3.render(v);
                }

            if (tankRed4 != null)
                if (tankRed4.getDispose() && tankRed4.getBullet() == null) {
                    tankRed4.clear();
                    if (tankRed4.getBullet() == null)
                        tankRed4 = new Tank(tbu, "left", ctr1, grid, lee);
                } else {
                    tankRed4.render(v);
                }
            if (tankRed5 != null)
                if (tankRed5.getDispose() && tankRed5.getBullet() == null) {
                    tankRed5.clear();
                    if (tankRed5.getBullet() == null)
                        tankRed5 = new Tank(tb4, "left", ctr5, grid, lee);
                } else {
                    tankRed5.render(v);
                }
        }
    }
}



