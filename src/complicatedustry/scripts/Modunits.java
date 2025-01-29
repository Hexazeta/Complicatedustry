package complicatedustry.scripts;

import arc.graphics.Color;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.gen.CrawlUnit;
import mindustry.gen.LegsUnit;
import mindustry.gen.Sounds;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class Modunits {
    public static UnitType
            //meld exclusive unit
            jack,xelura,

            kilo,omega,giga;

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
            hitSize = 14f;
            health = 1160;
            armor = 8f;
            outlineColor = Color.valueOf("595a66");
            rotateSpeed = 4.5f;
            legCount = 4;
            legLength = 16f;
            legForwardScl = 0.5f;
            legMoveSpace = 1.25f;
            legBaseOffset = 5f;
            legExtension = -2f;
            legLengthScl = 1f;
            groundLayer = Layer.legUnit;
            weapons.add(
                    new Weapon("mod-complicatedustry-kilo-pew"){{
                        top = false;
                        y = 0f;
                        x = 0f;
                        shootY = 4f;
                        reload = 105;
                        cooldownTime = 21f;
                        shake = 3f;
                        shootSound = Sounds.artillery;
                        rotate = false;
                        recoil = 1f;

                        bullet = new ArtilleryBulletType(5f, 8){{
                            shootEffect = new MultiEffect(Fx.shootSmallColor, new Effect(12, e -> {
                                color(Color.white, e.color, e.fin());
                                stroke(0.9f + e.fout());
                                Lines.square(e.x, e.y, e.fin() * 6f, e.rotation + 60f);

                                Drawf.light(e.x, e.y, 29f, e.color, e.fout() * 0.9f);
                            }));
                            hitEffect = despawnEffect = new MultiEffect(
                                    Fx.hitSquaresColor.wrap(Moditems.ultralloy.color, 45f),
                                    new WaveEffect(){{colorFrom = colorTo = Moditems.ultralloy.color;
                                sizeTo = splashDamageRadius - 64f;lifetime = 12f;strokeFrom = 3f;}});
                            knockback = 0.4f;
                            lifetime = 50f;
                            width = height = 13f;
                            collidesTiles = true;
                            ammoMultiplier = 3f;
                            splashDamageRadius = 37f;
                            splashDamage = 60f;
                            frontColor = lightningColor = trailColor = backColor = hitColor = Moditems.ultralloy.color;
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

        omega = new UnitType("omega"){{
            constructor = LegsUnit::create;
            aiController = GroundAI::new;
            drag = 0.33f;
            speed = 0.645f;
            hitSize = 27.5f;
            health = 2420;
            armor = 16f;
            outlineColor = Color.valueOf("595a66");
            rotateSpeed = 3.75f;
            legCount = 6;
            legLength = 24f;
            legForwardScl = 0.5f;
            legMoveSpace = 1.25f;
            legBaseOffset = 5f;
            legExtension = -2f;
            legLengthScl = 1f;
            groundLayer = Layer.legUnit;
            BulletType superSapper = new SapBulletType(){{
                sapStrength = 1.75f;
                length = 90f;
                damage = 90;
                shootEffect = Fx.hitSquaresColor.wrap(Moditems.ultralloy.color, 45f);
                hitColor = color = Moditems.ultralloy.color;
                despawnEffect = Fx.none;
                width = 0.9f;
                lifetime = 50f;
                knockback = -2.5f;
            }};
            weapons.add(
                    new Weapon("mod-complicatedustry-omega-pew"){{
                        reload = 128f;
                        rotateSpeed = 3f;
                        x = -14f;
                        y = 0f;
                        rotate = true;
                        bullet = superSapper;
                        shootSound = Sounds.sap;
                    }},
                    new Weapon("mod-complicatedustry-omega-pew"){{
                        reload = 96f;
                        rotateSpeed = 2f;
                        x = 7f;
                        y = -4f;
                        rotate = true;
                        bullet = superSapper;
                        shootSound = Sounds.sap;
                    }});
        }};

        giga = new UnitType("giga"){{
            constructor = LegsUnit::create;
            aiController = GroundAI::new;
            drag = 0.44f;
            speed = 0.48375f;
            hitSize = 37.5f;
            health = 7000;
            armor = 24f;
            outlineColor = Color.valueOf("595a66");
            rotateSpeed = 3f;
            legCount = 6;
            legLength = 32f;
            legForwardScl = 0.5f;
            legMoveSpace = 1.25f;
            legBaseOffset = 5f;
            legExtension = -2f;
            legLengthScl = 1f;
            groundLayer = Layer.legUnit;
        }};

    }}
