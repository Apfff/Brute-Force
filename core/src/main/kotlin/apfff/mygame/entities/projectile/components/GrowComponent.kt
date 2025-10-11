package apfff.mygame.entities.projectile.components

import apfff.mygame.entities.projectile.Projectile

class GrowComponent(
  val doubleTime: Float,
  val scaleFactor: Float
): BehaviorComponent {
  private var currentTime = 0f

  override fun step(projectile: Projectile, dt: Float) {
    currentTime += dt
    if (currentTime >= doubleTime) {
      projectile.scale *= scaleFactor
      currentTime -= doubleTime
    }

  }
}