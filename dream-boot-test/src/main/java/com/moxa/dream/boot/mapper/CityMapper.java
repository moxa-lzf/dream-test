/*
 *    Copyright 2015-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.moxa.dream.boot.mapper;

import com.moxa.dream.boot.spring.annotation.SessionControl;
import com.moxa.dream.boot.table.City;
import com.moxa.dream.driver.page.annotation.PageQuery;
import com.moxa.dream.module.annotation.Mapper;
import com.moxa.dream.module.annotation.Sql;

@Mapper
public interface CityMapper {
  @PageQuery
  @SessionControl
  @Sql("select id, name, state, country from city where state = @$(state)")
  City findByState(String state);
  @Sql("update city set name=null where state = @$(state)")
  void updateCity(String state);
  @Sql("update city set name=@$(name),state=@$(state) where id=@$(id)")
  Integer update(City city);
}
