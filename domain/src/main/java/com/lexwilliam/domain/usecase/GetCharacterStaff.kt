package com.lexwilliam.domain.usecase

import com.lexwilliam.domain.model.remote.detail.CharacterStaff
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCharacterStaff {
    suspend fun execute(id: Int): Flow<CharacterStaff>
}

class GetCharacterStaffImpl @Inject constructor(
    private val detailRepository: DetailRepository
): GetCharacterStaff {
    override suspend fun execute(id: Int): Flow<CharacterStaff> {
        return detailRepository.characterStaff(id)
    }
}