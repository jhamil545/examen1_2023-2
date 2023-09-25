package pe.edu.upeu.asistenciaupeujc.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.upeu.asistenciaupeujc.repository.EscuelaRepository
import pe.edu.upeu.asistenciaupeujc.repository.EscuelaRepositoryImp
import pe.edu.upeu.asistenciaupeujc.repository.UsuarioRepository
import pe.edu.upeu.asistenciaupeujc.repository.UsuarioRepositoryImp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun userRepository(userRepos:UsuarioRepositoryImp):UsuarioRepository
    @Binds
    @Singleton
    abstract fun escuelaRepository(actRepos:EscuelaRepositoryImp):EscuelaRepository

}