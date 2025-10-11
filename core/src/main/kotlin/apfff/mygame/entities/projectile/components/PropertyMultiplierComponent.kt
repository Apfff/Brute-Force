package apfff.mygame.entities.projectile.components

import apfff.mygame.entities.projectile.Projectile
import kotlin.reflect.KMutableProperty1

class PropertyMultiplierComponent<T: Number>(
  val property: KMutableProperty1<Projectile, T>,
  val finalMultiplier: Float,
  val duration: Float,
  val delay: Float = 0f,
  val scaleValue: (value: T, factor: Float) -> T,
  val easing: (durationFrac: Float) -> Float =  {it}
): BehaviorComponent {
  private var elapsedTime = 0f
  private var basePropertyValue: T? = null

  override fun step(projectile: Projectile, dt: Float) {
    elapsedTime += dt
    if(elapsedTime < delay) return
    if(basePropertyValue == null) basePropertyValue = property.get(projectile)
    val durationFrac = ((elapsedTime - delay)/duration).coerceIn(0f,1f)
    val eased = easing(durationFrac)
    val multiplyFactor = 1f + (finalMultiplier - 1f) * eased
    property.set(projectile, scaleValue(basePropertyValue!!, multiplyFactor))
  }
}