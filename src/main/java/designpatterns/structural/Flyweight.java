package designpatterns.structural;

import java.util.List;
import java.util.Optional;

public class Flyweight {

    class Skin {}

    class Theme {}

    class Particle {
        Skin skin;
        Theme theme;

        public Particle(Skin skin, Theme theme) {
            this.skin = skin;
            this.theme = theme;
        }
    }

    class Coordinate {}

    class Vector {}

    class MovingParticle extends Particle {
        Coordinate coordinate;
        Vector vector;

        public MovingParticle(Skin skin, Theme theme, Coordinate coordinate, Vector vector) {
            super(skin, theme);
            this.coordinate = coordinate;
            this.vector = vector;
        }
    }

    class ParticleFactory {
        List<Particle> particles;

        MovingParticle getParticle(Skin skin,
                                   Theme theme,
                                   Coordinate coordinate,
                                   Vector vector) {


            Particle particle = particles.stream().filter(p -> p.skin == skin && p.theme == theme)
                    .findAny()
                    .orElseGet(() -> {
                        Particle p = new Particle(skin, theme);
                        particles.add(p);
                        return p;
                    });
            return new MovingParticle(particle.skin,
                    particle.theme,
                    coordinate,
                    vector);

        }

        class Game {
            List<MovingParticle> movingParticles;
            ParticleFactory particleFactory;

            void shoot(Coordinate coordinate, Vector vector) {
                MovingParticle movingParticle = particleFactory.getParticle(new Skin(), new Theme(), coordinate, vector);
                movingParticles.add(movingParticle);
            }
        }

    }
}
