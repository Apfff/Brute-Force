package apfff.mygame.projectile.components

import com.badlogic.gdx.math.Vector2

class DragComponent(
  val drag: Float
): PhysicsComponent {
  override fun step(velocity: Vector2, dt: Float) {
    velocity.scl(1 - drag * dt)
  }

}