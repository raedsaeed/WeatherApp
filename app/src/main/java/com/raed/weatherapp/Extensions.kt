package com.raed.weatherapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.*
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment


/**
 * Created By Raed Saeed on 01/04/2022
 */

// Those are navigation-ui (androidx.navigation.ui) defaults
// used in NavigationUI for NavigationView and BottomNavigationView.
// Set yours here
val defaultNavOptions = navOptions {
    anim {
        enter = R.anim.slide_from_right_to_left
        exit = R.anim.slide_in_left
        popEnter = R.anim.slide_in_right
        popExit = R.anim.slide_from_left_to_right
    }
}

private val emptyNavOptions = navOptions {}

class NavHostFragmentWithDefaultAnimations : NavHostFragment() {

    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        navController.navigatorProvider.addNavigator(
            // this replaces FragmentNavigator
            FragmentNavigatorWithDefaultAnimations(requireContext(), childFragmentManager, id)
        )
    }

}

/**
 * Needs to replace FragmentNavigator and replacing is done with name in annotation.
 * Navigation method will use defaults for fragments transitions animations.
 */
@Navigator.Name("fragment")
class FragmentNavigatorWithDefaultAnimations(
    context: Context,
    manager: FragmentManager,
    containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        // this will try to fill in empty animations with defaults when no shared element transitions are set
        // https://developer.android.com/guide/navigation/navigation-animate-transitions#shared-element
        val shouldUseTransitionsInstead = navigatorExtras != null
        val navOptions = if (shouldUseTransitionsInstead) navOptions
        else navOptions.fillEmptyAnimationsWithDefaults()
        return super.navigate(destination, args, navOptions, navigatorExtras)
    }

    private fun NavOptions?.fillEmptyAnimationsWithDefaults(): NavOptions =
        this?.copyNavOptionsWithDefaultAnimations() ?: defaultNavOptions

    private fun NavOptions.copyNavOptionsWithDefaultAnimations(): NavOptions =
        let { originalNavOptions ->
            navOptions {
                launchSingleTop = originalNavOptions.shouldLaunchSingleTop()
                popUpTo(originalNavOptions.popUpToId) {
                    inclusive = originalNavOptions.isPopUpToInclusive()
                }
                anim {
                    enter =
                        if (originalNavOptions.enterAnim == emptyNavOptions.enterAnim) defaultNavOptions.enterAnim
                        else originalNavOptions.enterAnim
                    exit =
                        if (originalNavOptions.exitAnim == emptyNavOptions.exitAnim) defaultNavOptions.exitAnim
                        else originalNavOptions.exitAnim
                    popEnter =
                        if (originalNavOptions.popEnterAnim == emptyNavOptions.popEnterAnim) defaultNavOptions.popEnterAnim
                        else originalNavOptions.popEnterAnim
                    popExit =
                        if (originalNavOptions.popExitAnim == emptyNavOptions.popExitAnim) defaultNavOptions.popExitAnim
                        else originalNavOptions.popExitAnim
                }
            }
        }

}