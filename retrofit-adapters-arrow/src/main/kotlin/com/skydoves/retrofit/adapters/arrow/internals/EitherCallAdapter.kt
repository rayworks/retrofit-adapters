/*
 * Copyright (C) 2022 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.retrofit.adapters.arrow.internals

import arrow.core.Either
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * @author skydoves (Jaewoong Eum)
 * @since 1.0.0
 *
 * [CallAdapter] that delegates network call request and creates an instance of the [EitherCall].
 *
 * @property resultType Type of the result from the http request.
 * @property coroutineScope A coroutine scope that launches network requests.
 */
internal class EitherCallAdapter(
  private val resultType: Type,
  private val coroutineScope: CoroutineScope
) : CallAdapter<Type, Call<Either<Throwable, Type>>> {

  override fun responseType(): Type = resultType

  override fun adapt(call: Call<Type>): Call<Either<Throwable, Type>> {
    return EitherCall(call, coroutineScope)
  }
}
