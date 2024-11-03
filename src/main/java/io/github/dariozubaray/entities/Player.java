package io.github.dariozubaray.entities;

import io.github.dariozubaray.GamePanel;
import io.github.dariozubaray.GameState;
import io.github.dariozubaray.ImageLoader;
import io.github.dariozubaray.KeyHandler;

import io.github.dariozubaray.object.OBJ_Fireball;
import io.github.dariozubaray.object.OBJ_Key;
import io.github.dariozubaray.object.OBJ_Shield_Wood;
import io.github.dariozubaray.object.OBJ_Sword_Normal;
import io.github.dariozubaray.sound.SoundLabel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    KeyHandler keyHandler;

    public final int SCREEN_X;
    public final int SCREEN_Y;
    public int hasKey = 0;
    public boolean attackCanceled;
    int standCounter = 0;
    public List<Entity> inventory;
    public final int maxInventorySize;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.keyHandler = keyHandler;

        SCREEN_X = (gamePanel.SCREEN_WIDTH /2) - (gamePanel.TILE_SIZE /2);
        SCREEN_Y = (gamePanel.SCREEN_HEIGHT /2) - (gamePanel.TILE_SIZE /2);

        this.solidArea = new Rectangle();
        this.solidArea.x = 8;
        this.solidArea.y = 16;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        this.solidArea.width = 32;
        this.solidArea.height = 32;

        this.inventory = new ArrayList<>();
        this.maxInventorySize = 20;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setInventoryItem();
    }

    public void setDefaultValues() {
        this.worldX = gamePanel.TILE_SIZE * 23;
        this.worldY = gamePanel.TILE_SIZE * 21;
        this.speed = 4;
        this.direction = EntityDirection.DOWN;

        this.maxLife = 6;
        this.life = maxLife;
        this.level = 1;
        this.strength = 1;
        this.dexterity = 1;
        this.exp = 0;
        this.nextLevelExp = 5;
        this.coins = 0;
        this.currentWeapon = new OBJ_Sword_Normal(gamePanel);
        this.currentShield = new OBJ_Shield_Wood(gamePanel);
        this.projectile = new OBJ_Fireball(gamePanel);

        this.attack = getAttack();
        this.defense = getDefense();
    }

    public int getAttack() {
        this.attackArea = currentWeapon.attackArea;
        this.attackArea.height = 36;
        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return  defense = dexterity * currentShield.defenseValue;
    }

    public void getPlayerImage() {
        up1 = ImageLoader.loadSprite("/player/boy_up_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        up2 = ImageLoader.loadSprite("/player/boy_up_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down1 = ImageLoader.loadSprite("/player/boy_down_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        down2 = ImageLoader.loadSprite("/player/boy_down_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right1 = ImageLoader.loadSprite("/player/boy_right_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        right2 = ImageLoader.loadSprite("/player/boy_right_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left1 = ImageLoader.loadSprite("/player/boy_left_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        left2 = ImageLoader.loadSprite("/player/boy_left_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
    }

    public void getPlayerAttackImage() {
        if(currentWeapon.type.equals(EntityType.SWORD)) {
            attackUp1 = ImageLoader.loadSprite("/player/boy_attack_up_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackUp2 = ImageLoader.loadSprite("/player/boy_attack_up_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackDown1 = ImageLoader.loadSprite("/player/boy_attack_down_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackDown2 = ImageLoader.loadSprite("/player/boy_attack_down_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackRight1 = ImageLoader.loadSprite("/player/boy_attack_right_1.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
            attackRight2 = ImageLoader.loadSprite("/player/boy_attack_right_2.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
            attackLeft1 = ImageLoader.loadSprite("/player/boy_attack_left_1.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
            attackLeft2 = ImageLoader.loadSprite("/player/boy_attack_left_2.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
        }
        if(currentWeapon.type.equals(EntityType.AXE)) {
            attackUp1 = ImageLoader.loadSprite("/player/boy_axe_up_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackUp2 = ImageLoader.loadSprite("/player/boy_axe_up_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackDown1 = ImageLoader.loadSprite("/player/boy_axe_down_1.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackDown2 = ImageLoader.loadSprite("/player/boy_axe_down_2.png", gamePanel.TILE_SIZE, gamePanel.TILE_SIZE * 2);
            attackRight1 = ImageLoader.loadSprite("/player/boy_axe_right_1.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
            attackRight2 = ImageLoader.loadSprite("/player/boy_axe_right_2.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
            attackLeft1 = ImageLoader.loadSprite("/player/boy_axe_left_1.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
            attackLeft2 = ImageLoader.loadSprite("/player/boy_axe_left_2.png", gamePanel.TILE_SIZE * 2, gamePanel.TILE_SIZE);
        }
    }

    public void setInventoryItem() {
        this.inventory.add(currentWeapon);
        this.inventory.add(currentShield);
        this.inventory.add(new OBJ_Key(gamePanel));
        this.inventory.add(new OBJ_Key(gamePanel));
    }

    @Override
    public void update() {
        boolean isPlayerMoving = keyHandler.upPressed || keyHandler.downPressed || keyHandler.rightPressed || keyHandler.leftPressed;
        if(attacking) {
            attackingMode();
        } else if (isPlayerMoving || keyHandler.enterPressed) {
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npcsArray);
            interactNpc(npcIndex);

            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monstersArray);
            contactMonster(monsterIndex);

            if(isPlayerMoving) {
                setDirection();
                movePlayer();
            }
            gamePanel.eventHandler.checkEvent();
            invertSprites();
            if (gamePanel.keyHandler.enterPressed && !attackCanceled) {
                attacking = true;
                spriteCounter = 0;
                gamePanel.playSoundEffect(SoundLabel.SWING_WEAPON.getAudioIndex());
            }
            attackCanceled = false;
            gamePanel.keyHandler.enterPressed = false;
        } else {
            setStandUp();
        }

        shotProjectile();
        checkInvincibility();
    }

    private void attackingMode() {
        spriteCounter++;

        if(spriteCounter <= 5) {
            spriteNumber = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNumber = 2;

            checkDamageMonster();
        }
        if(spriteCounter > 25) {
            spriteNumber = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    private void checkDamageMonster() {
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        switch (direction) {
            case UP -> worldY -= attackArea.height;
            case DOWN -> worldY += attackArea.height;
            case LEFT -> worldX -= attackArea.width;
            case RIGHT -> worldX += attackArea.width;
        }
        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monstersArray);
        damageMonster(monsterIndex, attack);

        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;
    }

    public void damageMonster(int index, int attack) {
        if (index == -1) {
            return;
        }

        var monster = gamePanel.monstersArray[index];
        if(!monster.invincible) {
            gamePanel.playSoundEffect(SoundLabel.HIT_MONSTER.getAudioIndex());
            int damage = attack - monster.defense;
            if(damage < 0) damage = 0;

            monster.life -= damage;
            monster.invincible = true;
            monster.damageReaction();
            gamePanel.ui.addMessage(damage + " damage!");

            if(monster.life <= 0) {
                monster.dying = true;
                gamePanel.ui.addMessage("Killed the " + gamePanel.monstersArray[index].name.getName() + "!");
                exp += monster.exp;
                gamePanel.ui.addMessage("Exp +" + monster.exp);
                checkLevelUp();
            }
        }
    }

    public void checkLevelUp() {
        if(exp <= nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gamePanel.playSoundEffect(SoundLabel.LEVEL_UP.getAudioIndex());
            gamePanel.gameState = GameState.DIALOGUE;
            gamePanel.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem() {
        int itemIndex = gamePanel.ui.getItemIndexOnSlot();

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            if (selectedItem.type.equals(EntityType.SWORD) || selectedItem.type.equals(EntityType.AXE)) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type.equals(EntityType.SHIELD)) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type.equals(EntityType.CONSUMABLE)) {
                selectedItem.use(this);
                inventory.remove(itemIndex);
            }
        }
    }

    private void pickUpObject(int index) {
        if (index == -1) {
            return;
        }

        if(inventory.size() == maxInventorySize) {
            return;
        }

        inventory.add(gamePanel.objectsArray[index]);
        EntityLabel entityLabel = gamePanel.objectsArray[index].name;
        switch (entityLabel) {
            case KEY -> {
                gamePanel.playSoundEffect(SoundLabel.COIN.getAudioIndex());
                hasKey++;
                gamePanel.objectsArray[index] = null;
                gamePanel.ui.addMessage("You get a key!");
            }
            case DOOR -> {
                if (hasKey > 0) {
                    gamePanel.playSoundEffect(SoundLabel.UNLOCK.getAudioIndex());
                    gamePanel.objectsArray[index] = null;
                    hasKey--;
                    gamePanel.ui.addMessage("You opened the door!");
                } else {
                    gamePanel.ui.addMessage("You need a key!");
                }
            }
            case BOOT -> {
                gamePanel.playSoundEffect(SoundLabel.POWER_UP.getAudioIndex());
                gamePanel.objectsArray[index] = null;
                gamePanel.ui.addMessage("You got the speed boots!");
                speed += 2;
            }
            case CHEST -> {
                gamePanel.ui.gameFinished = true;
                gamePanel.stopMusic();
                gamePanel.playSoundEffect(SoundLabel.FANFARE.getAudioIndex());
            }
            default -> {
                gamePanel.playSoundEffect(SoundLabel.COIN.getAudioIndex());
                gamePanel.ui.addMessage("You get a " + gamePanel.objectsArray[index].name.getName() + "!");
                gamePanel.objectsArray[index] = null;
            }
        }
    }

    private void interactNpc(int index) {
        if (index == -1) {
            return;
        }

        if(gamePanel.keyHandler.enterPressed) {
            attackCanceled = true;
            gamePanel.gameState = GameState.DIALOGUE;
            gamePanel.npcsArray[index].speak();
        }
    }

    private void contactMonster(int monsterIndex) {
        if(monsterIndex == -1) {
            return;
        }

        var monster = gamePanel.monstersArray[monsterIndex];
        if(!invincible && !monster.alive) {
            gamePanel.playSoundEffect(SoundLabel.RECEIVE_DAMAGE.getAudioIndex());
            int damage = monster.attack - defense;

            if(damage < 0) damage = 0;

            this.life -= damage;
            this.invincible = true;
        }
    }

    private void setStandUp() {
        standCounter++;
        if (standCounter >= 60) {
            standCounter = 0;
            spriteNumber = 1;
        }
    }

    private void shotProjectile() {
        if(gamePanel.keyHandler.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);
            gamePanel.projectileList.add(projectile);

            shotAvailableCounter = 0;
            gamePanel.playSoundEffect(SoundLabel.BURNING.getAudioIndex());
        }

        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    private void checkInvincibility() {
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 120) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    private void setDirection() {
        if (keyHandler.upPressed) {
            this.direction = EntityDirection.UP;
        } else if (keyHandler.downPressed) {
            this.direction = EntityDirection.DOWN;
        } else if (keyHandler.rightPressed) {
            this.direction = EntityDirection.RIGHT;
        } else if(keyHandler.leftPressed) {
            this.direction = EntityDirection.LEFT;
        }
    }

    private void movePlayer() {
        if (!collisionOn && !gamePanel.keyHandler.enterPressed) {
            switch (this.direction) {
                case UP -> this.worldY -= speed;
                case DOWN -> this.worldY += speed;
                case LEFT -> this.worldX -= speed;
                case RIGHT -> this.worldX += speed;
            }
        }
    }

    private void invertSprites() {
        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNumber == 1) spriteNumber = 2;
            else if (spriteNumber == 2) spriteNumber = 1;

            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = SCREEN_X;
        int tempScreenY = SCREEN_Y;

        switch (direction) {
            case UP -> {
                if (!attacking) {
                    if (this.spriteNumber == 1) image = up1;
                    if (this.spriteNumber == 2) image = up2;
                } else {
                    tempScreenY -= gamePanel.TILE_SIZE;
                    if (this.spriteNumber == 1) image = attackUp1;
                    if (this.spriteNumber == 2) image = attackUp2;
                }
            }
            case DOWN -> {
                if (!attacking) {
                    if (this.spriteNumber == 1) image = down1;
                    if (this.spriteNumber == 2) image = down2;
                } else {
                    if (this.spriteNumber == 1) image = attackDown1;
                    if (this.spriteNumber == 2) image = attackDown2;
                }
            }
            case RIGHT -> {
                if (!attacking) {
                    if (this.spriteNumber == 1) image = right1;
                    if (this.spriteNumber == 2) image = right2;
                } else {
                    if (this.spriteNumber == 1) image = attackRight1;
                    if (this.spriteNumber == 2) image = attackRight2;
                }
            }
            case LEFT -> {
                if (!attacking) {
                    if (this.spriteNumber == 1) image = left1;
                    if (this.spriteNumber == 2) image = left2;
                } else {
                    tempScreenX -= gamePanel.TILE_SIZE;
                    if (this.spriteNumber == 1) image = attackLeft1;
                    if (this.spriteNumber == 2) image = attackLeft2;
                }
            }
        }
        int x = tempScreenX, y = tempScreenY;
        if(SCREEN_X > worldX) x = worldX;
        if(SCREEN_Y > worldY) y = worldY;

        int rightOffset = gamePanel.SCREEN_WIDTH - tempScreenX;
        if(rightOffset > gamePanel.WORLD_WIDTH - worldX) x = gamePanel.SCREEN_WIDTH - (gamePanel.WORLD_WIDTH - worldX);
        int bottomOffset = gamePanel.SCREEN_HEIGHT - tempScreenY;
        if(bottomOffset > gamePanel.WORLD_HEIGHT - worldY) y = gamePanel.SCREEN_HEIGHT - (gamePanel.WORLD_HEIGHT - worldY);

        if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g2.drawImage(image, x, y, null);
        if(invincible) g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        if (keyHandler.debugMode) {
            g2.setColor(Color.RED);
            g2.drawRect(this.SCREEN_X + solidArea.x, this.SCREEN_Y + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
