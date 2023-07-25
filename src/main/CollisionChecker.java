package main;

import entity.Entity;

import java.util.Objects;

public class CollisionChecker {
    GamePanel gamePanel;
    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/ gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY/ gamePanel.tileSize;

        int tileNum1, tileNum2;

        if (Objects.equals(entity.direction, "up")) {

            entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
            tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

            if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        if (Objects.equals(entity.direction, "down")) {

            entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
            tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

            if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        if (Objects.equals(entity.direction, "left")) {

            entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
            tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

            if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
        if (Objects.equals(entity.direction, "right")) {

            entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
            tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

            if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
    }
}
