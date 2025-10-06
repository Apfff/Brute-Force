package apfff.mygame

import com.badlogic.gdx.math.Vector2
import ktx.math.div
import ktx.math.plus
import ktx.math.times

interface Projectile {
  val pos: Vector2
  val prevPos: Vector2
  val velocity: Vector2

  fun apply(force: Vector2)

  fun step(dt: Float)
}