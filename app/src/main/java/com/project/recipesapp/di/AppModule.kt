package com.project.recipesapp.di

import android.app.Application
import androidx.room.Room
import com.project.recipesapp.feature_recipe.data.data_source.RecipeDatabase
import com.project.recipesapp.feature_recipe.data.repository.RecipeRepositoryImpl
import com.project.recipesapp.feature_recipe.domain.repository.RecipeRepository
import com.project.recipesapp.feature_recipe.domain.use_case.AddRecipeUseCase
import com.project.recipesapp.feature_recipe.domain.use_case.DeleteRecipeUseCase
import com.project.recipesapp.feature_recipe.domain.use_case.GetRecipeUseCase
import com.project.recipesapp.feature_recipe.domain.use_case.GetRecipesUseCase
import com.project.recipesapp.feature_recipe.domain.use_case.RecipeUseCases
import com.project.recipesapp.feature_recipe_online.domain.repository.RecipeApiRepository
import com.project.recipesapp.feature_recipe_online.domain.use_case.GetRecipesSortedByNameAndOrderUseCase
import com.project.recipesapp.feature_recipe_online.domain.use_case.SuggestedRecipesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Volatile
    private var _instance: RecipeDatabase? = null

    @Provides
    @Singleton
    fun provideRecipeDatabase(application: Application) : RecipeDatabase {
        return _instance ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                application,
                RecipeDatabase::class.java,
                RecipeDatabase.DATABASE_NAME
            ).build()
            _instance = instance
            instance
        }
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(db: RecipeDatabase) : RecipeRepository {
        return RecipeRepositoryImpl(db.recipeDao)
    }

    @Provides
    @Singleton
    fun provideRecipeUseCase(recipeRepository: RecipeRepository) : RecipeUseCases {
        return RecipeUseCases(
            getRecipesUseCase = GetRecipesUseCase(recipeRepository),
            deleteRecipeUseCase = DeleteRecipeUseCase(recipeRepository),
            addRecipeUseCase = AddRecipeUseCase(recipeRepository),
            getRecipeUseCase = GetRecipeUseCase(recipeRepository)
        )
    }

    @Provides
    @Singleton
    fun provideSuggestedRecipesUseCases(recipeApiRepository: RecipeApiRepository) : SuggestedRecipesUseCases {
        return SuggestedRecipesUseCases(
            getRecipesSortedByNameAndOrderUseCase = GetRecipesSortedByNameAndOrderUseCase(recipeApiRepository)
        )
    }

    @Provides
    @Singleton
    fun provideRecipeApiRepository() : RecipeApiRepository {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiRepository::class.java)
    }

}