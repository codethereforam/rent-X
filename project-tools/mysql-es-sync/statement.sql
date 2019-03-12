select id,
       category_id    categoryId,
       name,
       description,
       deposit,
       rental,
       status,
       picture_id     pictureId,
       user_id        userId,
       add_user_id    addUserId,
       add_time       addTime,
       update_user_id updateUserId,
       update_time    updateTime,
       mark
from stuff
where update_time > :sql_last_value