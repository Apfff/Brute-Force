package apfff.mygame

import com.badlogic.gdx.math.Vector2
import ktx.math.plusAssign
import kotlin.ranges.step

class PhysicsService(
  val tickDt: Float,
  val projectiles: ArrayList<Projectile>
): Service {

  val impulseBuffer = ArrayList<Impulse>()


  fun step(){
    for (projectile in projectiles) {
      projectile.step(tickDt)
    }
  }

  fun addImpulse(impulse: Impulse) {
    impulseBuffer.add(impulse)
  }

  fun resetImpulses(){
    impulseBuffer.clear()
  }

  fun getImpulses(): ArrayList<Impulse> {
    return impulseBuffer
  }

  fun computeForcesAt(pos: Vector2): Vector2{
    val result = Vector2()
    for (impulse in impulseBuffer){
      result += impulse.computeForceAt(pos)
    }
    return result
  }

  fun applyForces(){
    for (projectile in projectiles) {
      projectile.apply(computeForcesAt(projectile.pos))
    }
    resetImpulses()
  }

  override fun reset(){
    resetImpulses()
  }
}