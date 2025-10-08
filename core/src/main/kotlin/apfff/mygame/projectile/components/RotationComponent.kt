package apfff.mygame.projectile.components

import com.badlogic.gdx.math.Vector2

class RotationComponent(
  val rotation: Float
): PhysicsComponent {
  override fun step(velocity: Vector2, dt: Float) {
    velocity.rotateDeg(rotation * dt)
  }
}