package entity.core.constants;

public interface Constants {

    // Screen settings
    int ORIGINAL_TILE_SIZE = 16;
    int SCALE = 3;
    int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    // World settings
    int MAX_SCREEN_COLUMN = 32;
    int MAX_SCREEN_ROW = 24;
    int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
    int MAX_WORLD_COLUMN = 50;
    int MAX_WORLD_ROW = 50;
}
