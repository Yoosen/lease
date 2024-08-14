package com.uu.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uu.lease.common.result.Result;
import com.uu.lease.model.entity.AttrKey;
import com.uu.lease.model.entity.AttrValue;
import com.uu.lease.web.admin.service.AttrKeyService;
import com.uu.lease.web.admin.service.AttrValueService;
import com.uu.lease.web.admin.vo.attr.AttrKeyVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "房间属性管理")
@RestController
@RequestMapping("/admin/attr")
public class AttrController {


    @Autowired
    private AttrKeyService service;

    @Autowired
    private AttrValueService valueService;

    @Autowired
    private AttrKeyService keyService;

    @Operation(summary = "新增或更新属性名称")
    @PostMapping("key/saveOrUpdate")
    public Result saveOrUpdateAttrKey(@RequestBody AttrKey attrKey) {
        service.saveOrUpdate(attrKey);
        return Result.ok();
    }

    @Operation(summary = "新增或更新属性值")
    @PostMapping("value/saveOrUpdate")
    public Result saveOrUpdateAttrValue(@RequestBody AttrValue attrValue) {
        valueService.saveOrUpdate(attrValue);
        return Result.ok();
    }


    @Operation(summary = "查询全部属性名称和属性值列表")
    @GetMapping("list")
    public Result<List<AttrKeyVo>> listAttrInfo() {
        List<AttrKeyVo> lists = keyService.listAttrInfo();
        return Result.ok(lists);
    }

    @Operation(summary = "根据id删除属性名称")
    @DeleteMapping("key/deleteById")
    public Result removeAttrKeyById(@RequestParam Long attrKeyId) {
        // 删除属性名称
        keyService.removeById(attrKeyId);

        // 删除属性值
        LambdaQueryWrapper<AttrValue> attrValueQueryWrapper = new LambdaQueryWrapper<>();
        attrValueQueryWrapper.eq(AttrValue::getAttrKeyId, attrKeyId);
        valueService.remove(attrValueQueryWrapper);

        return Result.ok();
    }

    @Operation(summary = "根据id删除属性值")
    @DeleteMapping("value/deleteById")
    public Result removeAttrValueById(@RequestParam Long id) {
        valueService.removeById(id);
        return Result.ok();
    }

}
