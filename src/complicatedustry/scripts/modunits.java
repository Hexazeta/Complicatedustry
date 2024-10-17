package complicatedustry.scripts;

import arc.graphics.Color;
import mindustry.Vars;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
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

            weapons.add(new Weapon("complicatedustry-jack-cannon"){{
                layerOffset = -0.00001f;
                top = false;
                x = -14;
                y = 0;

                recoil = 2f;
                shake = 1f;
                ejectEffect = Fx.casing3;
                shoot.shots = 2;
                shoot.shotDelay = 4f;
                alternate = true;
                mirror = false;
                reload = 24f;
                inaccuracy = 5f;

                shootSound = Vars.tree.loadSound("pewpewpew");
                bullet = new BasicBulletType(6f, 36){{
                    hitEffect = Fx.blastExplosion;
                    width = 11;
                    height = 19;
                    fragBullets = 5;
                    fragLifeMin = 0f;
                    fragRandomSpread = 60f;
                }};
            }});

            weapons.add(new Weapon("complicatedustry-jack-flamethrower"){{
                layerOffset = -0.00001f;
                top = false;
                x = 14;
                y = 0;

                recoil = 2f;
                shake = 1f;
                ejectEffect = Fx.casing3;
                shoot.shots = 2;
                shoot.shotDelay = 4f;
                alternate = true;
                mirror = false;
                reload = 24f;
                inaccuracy = 5f;

                shootSound = Vars.tree.loadSound("pewpewpew");
                bullet = new BasicBulletType(6f, 36){{
                    hitEffect = Fx.blastExplosion;
                    width = 11;
                    height = 19;
                    fragBullets = 5;
                    fragLifeMin = 0f;
                    fragRandomSpread = 60f;
                }};
            }});

            legCount = 4;
            legLength = 32f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -4f;
            legBaseOffset = 1f;
            legMaxLength = 1f;
            legMinLength = 1f;
            legLengthScl = 0.5f;
            legForwardScl = 0.5f;
            rippleScale = 0.5f;
            rotateToBuilding = true;
            legMoveSpace = 2f;
            allowLegStep = true;
            legPhysicsLayer = false;

            speed = 1f;
            drag = 0.11f;
            hitSize = 24f;
            rotateSpeed = 3f;
            health = 3600;
            armor = 100f;

            outlineColor = Color.valueOf("2b2626");

    }};

    }}
