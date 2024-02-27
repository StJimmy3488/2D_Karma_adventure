package entity.core.constants;

public interface Constants {

    // Screen settings
    int ORIGINAL_TILE_SIZE = 16;
    int SCALE = 3;
    int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    int HALF_TILE_SIZE = TILE_SIZE/2;

    // World settings
    int MAX_SCREEN_COLUMN = 32;
    int MAX_SCREEN_ROW = 24;
    int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    int HALF_SCREEN_WIDTH = SCREEN_WIDTH/2;
    int HALF_SCREEN_HEIGHT = SCREEN_HEIGHT/2;
    int MAX_WORLD_COLUMN = 50;
    int MAX_WORLD_ROW = 50;

    // Player settings
    int PLAYER_ANIMATION_DELAY = 16;
    int PLAYER_START_POSITION_X = TILE_SIZE * 21;
    int PLAYER_START_POSITION_Y = TILE_SIZE * 21;
    int PLAYER_CAMERA_POSITION_X = HALF_SCREEN_WIDTH - HALF_TILE_SIZE;
    int PLAYER_CAMERA_POSITION_Y = HALF_SCREEN_HEIGHT - HALF_TILE_SIZE;

    // Paths
    String TEXTURE_FOLDER_PATH = "src/main/java/res/textures";
    String MAP_PATH = "src/main/java/res/maps/map_2.txt";




}
