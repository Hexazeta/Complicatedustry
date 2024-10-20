package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.Vars;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ContinuousFlameBulletType;
import mindustry.gen.LegsUnit;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class modunits {
    public static UnitType
            //meld exclusive unit
            jack;

    public static void load() {
        jack = new UnitType("jack"){{
            constructor = LegsUnit::create;
            aiController = GroundAI::new;

            weapons.add(new Weapon("mod-complicatedustry-jack-cannon"){{
                layerOffset = -0.00001f;
                top = false;
                x = -36;
                y = 0;
                shootY = 26;
                recoil = 4f;
                shake = 3f;
                alternate = false;
                mirror = false;
                reload = 120f;

                bullet = new BasicBulletType(10f, 1120){{
                    hitEffect = Fx.blastExplosion;
                    width = 23;
                    height = 37;
                    knockback = 15;
                    pierceCap = 8;
                }};
            }});

            weapons.add(new Weapon("mod-complicatedustry-jack-flamethrower"){{
                layerOffset = -0.00001f;
                top = false;
                x = 36;
                y = 0;
                shootY = 24;
                recoil = 2f;
                shake = 1f;
                continuous = true;
                alwaysContinuous = true;
                alternate = false;
                mirror = false;

                bullet = new ContinuousFlameBulletType(){{
                    hitEffect = Fx.blastExplosion;
                    width = 5;
                    length = 160;
                    flareLength = 32;
                    flareWidth = 8;
                    knockback = 5;
                    pierceCap = 5;
                    rangeOverride = 120;
                    damage = 970f/12f;
                }};
            }});

            legCount = 4;
            legLength = 64f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -7.5f;
            legBaseOffset = 5.5f;
            legLengthScl = 0.8f;
            stepShake = 3f;
            rippleScale = 2f;
            rotateToBuilding = true;
            legMoveSpace = 1f;
            allowLegStep = true;
            legPhysicsLayer = false;

            speed = 0.35f;
            drag = 0.11f;
            hitSize = 50f;
            rotateSpeed = 1f;
            health = 9000;
            armor = 185f;

            outlineColor = Color.valueOf("2b2626");

    }};

    }}
