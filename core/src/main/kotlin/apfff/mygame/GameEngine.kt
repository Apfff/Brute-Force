package apfff.mygame

import apfff.mygame.impulse.Impulse
import apfff.mygame.projectile.Projectile
import com.badlogic.gdx.Gdx

class GameEngine(
  tps: Int
) {
  private val tickDt: Float = 1f/tps
  private var timeAccumulation = 0f

  private val projectiles = ArrayList<Projectile>()
  private val pendingProjectilesBuffer = ArrayList<Projectile>()
  private val pendingImpulsesBuffer = ArrayList<Impulse>()
  private var applyForcesBuffer = false //temp

  private val destroyQueue = ArrayDeque<Pair<MutableCollection<out PhysicEntity>, PhysicEntity>>()

  private val physicsService = PhysicsService(tickDt, projectiles)

  fun update(dt: Float){
    timeAccumulation += dt
    while (timeAccumulation >= tickDt){
      //create bodies
      addPending()
      //handle physics
      if(applyForcesBuffer){
        applyForces()
      }
      physicsService.step()
      //game state
      for(p in projectiles){
        if(p.pos.x < 0 || p.pos.x > Gdx.graphics.width ||
          p.pos.y < 0 || p.pos.y > Gdx.graphics.height){
          p.trigger(TriggerEvent.OUT_OF_BOUNDS)
        }
      }
      destroyPhysicEntities()

      timeAccumulation -= tickDt
      timeAccumulation = timeAccumulation.coerceAtLeast(0f)
    }
  }

  private fun addPending(){
    projectiles.addAll(pendingProjectilesBuffer)
    physicsService.getImpulses().addAll(pendingImpulsesBuffer)
    pendingProjectilesBuffer.clear()
    pendingImpulsesBuffer.clear()
  }

  private fun applyForces(){
    physicsService.applyForces()
    applyForcesBuffer = false
  }

  private fun destroyPhysicEntities(){
    for ((list, entity) in destroyQueue){
      if(!list.contains(entity)) continue
      list.remove(entity)
    }
    destroyQueue.clear()
  }

  fun addImpulse(vararg impulse: Impulse) {
    pendingImpulsesBuffer.addAll(impulse)
  }
  fun addProjectile(vararg projectile: Projectile) {
    for (p in projectile){
      p.on(TriggerEvent.EXPIRE){p -> removeProjectile(p)}
      p.on(TriggerEvent.OUT_OF_BOUNDS){p -> removeProjectile(p)}
    }
    pendingProjectilesBuffer.addAll(projectile)
  }
  fun removeProjectile(vararg projectile: Projectile) {
    for (p in projectile){
      destroyQueue.addLast(projectiles to p)
    }
  }

  fun requestApplyForces(){
    applyForcesBuffer = true
  }

  fun getProjectiles(): ArrayList<Projectile> {
    return projectiles
  }
  fun getImpulses(): ArrayList<Impulse> {
    return physicsService.getImpulses()
  }

  fun getAlpha(): Float{
    return timeAccumulation / tickDt
  }

  fun reset(){
    timeAccumulation = 0f
    projectiles.clear()
    pendingProjectilesBuffer.clear()
    pendingImpulsesBuffer.clear()
    applyForcesBuffer = false
    physicsService.reset()
  }

}