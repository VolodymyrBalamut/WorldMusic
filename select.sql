select v.mark, c.name, us.name  from voting v
left join clips c on c.id = v.clip_id
left join users us on us.user_id = v.user_id