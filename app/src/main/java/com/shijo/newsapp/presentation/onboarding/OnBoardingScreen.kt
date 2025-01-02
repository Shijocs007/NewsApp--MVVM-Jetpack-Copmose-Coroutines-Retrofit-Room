package com.shijo.newsapp.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loc.newsapp.presentation.onboarding.components.OnBoardingPage
import com.loc.newsapp.presentation.onboarding.components.PagerIndicator
import com.shijo.newsapp.presentation.common.NewsButton
import com.shijo.newsapp.presentation.common.NewsTextButton
import com.shijo.newsapp.presentation.onboarding.components.pages
import com.shijo.newsapp.ui.theme.Dimes
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }
                .distinctUntilChanged()  // Only react to changes in the page index
                .collect { pageIndex ->
                    onEvent(OnboardingEvent.OnboardingSwiped(selectedPage = pageIndex))
                }
        }

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimes.PaddingMedium)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(
                modifier = Modifier.width(52.dp),
                pagesSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                //Hide the button when the first element of the list is empty
                if ((state as OnboardingState.OnboardingCurrentPageState).button[0].isNotEmpty()) {
                    NewsTextButton(
                        text = state.button[0],
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage - 1
                                )
                            }

                        }
                    )
                }
                NewsButton(
                    text = state.button[1],
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                               onEvent(OnboardingEvent.SaveOnboardingCompleted)
                            } else {
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}