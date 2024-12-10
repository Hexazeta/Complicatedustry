package complicatedustry.scripts;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.Vars;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ContinuousFlameBulletType;
import mindustry.entities.bullet.SapBulletType;
import mindustry.gen.CrawlUnit;
import mindustry.gen.LegsUnit;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.ammo.PowerAmmoType;

public class modunits {
    public static UnitType
            //meld exclusive unit
            jack,xelura,

            kilo;

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
                bullet = new BasicBulletType(10f, 1200){{
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
                    damage = 980f/12f;
                }};
            }});
            //why the fuck are the upper legs not visible//not anymore
            legCount = 4;
            legLength = 52f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -1f;
            legBaseOffset = 20f;
            legLengthScl = 1f;
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

        xelura = new UnitType("xelura"){{
        constructor = CrawlUnit::create;
        aiController = GroundAI::new;

        omniMovement = false;
        segments = 8;
        segmentScl = 32;
    }};

        kilo = new UnitType("kilo"){{
            constructor = LegsUnit::create;
            aiController = GroundAI::new;
            drag = 0.22f;
            speed = 0.86f;
            hitSize = 8.5f;
            health = 1160;
            armor = 8f;
            outlineColor = Color.valueOf("595a66");
            rotateSpeed = 4.5f;

            legStraightness = 0.5f;
            legCount = 4;
            legLength = 12f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -3f;
            legBaseOffset = 6f;
            legMaxLength = 1.2f;
            legMinLength = 0.5f;
            legLengthScl = 2.5f;
            legPairOffset = 5f;
            legForwardScl = 1f;
            rippleScale = 0.4f;
            legMoveSpace = 2f;
            groundLayer = Layer.legUnit;
            weapons.add(
                    new Weapon("mod-complicatedustry-kilo-pew"){{
                        top = false;
                        y = 2f;
                        x = 0f;
                        shootY = 4f;
                        reload = 105;
                        cooldownTime = 21f;
                        shake = 3f;
                        ejectEffect = Fx.casing1;
                        shootSound = Sounds.artillery;
                        rotate = false;
                        recoil = 1f;

                        bullet = new ArtilleryBulletType(5f, 8){{
                            hitEffect = Fx.sapExplosion.wrap(moditems.ultralloy.color, 30f);
                            knockback = 0.4f;
                            lifetime = 50f;
                            width = height = 13f;
                            collidesTiles = true;
                            ammoMultiplier = 3f;
                            splashDamageRadius = 37f;
                            splashDamage = 60f;
                            frontColor = lightningColor = trailColor = backColor = hitColor = moditems.ultralloy.color;
                            lightning = 6;
                            lightningLength = 5;
                            smokeEffect = Fx.shootBigSmoke2;
                            shake = 2.5f;
                            mirror = false;
                            trailLength = 32;
                            trailWidth = 3f;
                            trailEffect = Fx.none;
                            trailInterp = Interp.slope;

                            shrinkX = 0.9f;
                            shrinkY = 0.3f;
                        }};
                    }});
        }};

    }}
