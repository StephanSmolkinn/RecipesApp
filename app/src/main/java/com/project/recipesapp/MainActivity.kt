package com.project.recipesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.recipesapp.feature_recipe.presentation.add_edit_recipe.AddEditScreen
import com.project.recipesapp.feature_recipe.presentation.recipes.RecipesScreen
import com.project.recipesapp.feature_recipe.presentation.util.Screen
import com.project.recipesapp.feature_recipe.presentation.util.TabItem
import com.project.recipesapp.feature_recipe_online.presentation.suggested_recipes.SuggestedRecipesScreen
import com.project.recipesapp.navigation.Navigation
import com.project.recipesapp.ui.theme.RecipesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tabs = listOf(
            TabItem(
                selected = Icons.Filled.Home,
                unselected = Icons.Outlined.Home
            ),
            TabItem(
                selected = Icons.Filled.Search,
                unselected = Icons.Outlined.Search
            )
        )

        setContent {
            RecipesAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()

                    val tabIndex = remember {
                        mutableIntStateOf(0)
                    }

                    val pager = rememberPagerState {
                        tabs.size
                    }

                    Scaffold {
                        LaunchedEffect(tabIndex.intValue) {
                            pager.animateScrollToPage(tabIndex.intValue)
                        }

                        LaunchedEffect(pager.currentPage, pager.isScrollInProgress) {
                            if (!pager.isScrollInProgress)
                                tabIndex.intValue = pager.currentPage
                        }

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            TabRow(selectedTabIndex = tabIndex.intValue) {
                                tabs.forEachIndexed { index, tabItem ->
                                    Tab(
                                        selected = index == tabIndex.intValue,
                                        onClick = {
                                            tabIndex.intValue = index
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (index == tabIndex.intValue) tabItem.selected else tabItem.unselected,
                                                contentDescription = null
                                            )
                                        }
                                    )
                                }
                            }

                            HorizontalPager(
                                state = pager,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) { index ->
                                when (index) {
                                    0 -> {
                                        Navigation(modifier = Modifier.padding(it))
                                    }

                                    1 -> {
                                        SuggestedRecipesScreen()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
