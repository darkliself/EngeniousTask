package com.darkliself.engenioustask.ui.screens.mainscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.darkliself.engenioustask.core.ConstantSize
import com.darkliself.engenioustask.core.ConstantText
import com.darkliself.engenioustask.data.room.entity.UserEntity

@Composable
fun UserCard(user: UserEntity, modifier: Modifier, shape: Shape, color: Color) {
    ElevatedCard(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(color)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(ConstantSize.PADDING_DEFAULT)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = ConstantText.USER_CARD_CONTENT_DESCRIPTION + user.login,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(ConstantSize.CORNER_RADIUS_LARGE))
                    .weight(1f),
            )

            Spacer(modifier = Modifier.width(ConstantSize.MEDIUM_SPACER_HEIGHT))

            Text(
                text = user.login,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}