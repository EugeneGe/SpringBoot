package ${package.Controller};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger2>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if swagger2>
@Api(value = "${table.controllerName}", tags = "${table.controllerName}控制器")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>
    private final static Logger logger = LoggerFactory.getLogger(${table.controllerName}.class);

    @Autowired
    private ${table.serviceName} i${entity}Service;

    /**
     * ${table.comment}新增
     *
     * @param ${table.entityPath} ${entity} 对象
     * @return Result
     */
    <#if swagger2>
    @ApiOperation(value = "${table.comment}信息", notes = "新增")
    </#if>
    @ResponseBody
    @PostMapping("/add")
    <#--public ResultVo<String> add(@RequestBody ${entity} ${table.entityPath}) {
        ResultVo<String> result = new ResultVo<>();
        try {
            if (i${entity}Service.save(${table.entityPath})) {
                result.resultSuccess("新增成功!");
            } else {
                result.resultFail("新增失败!");
            }
        } catch (Exception e) {
            result.resultFail("新增异常！");
            logger.error(e.getMessage(),e);
        }
        return result;
    }-->
    public ResponseEntity<String> add(@RequestBody ${entity} ${table.entityPath}) {
        try {
        if (i${entity}Service.save(${table.entityPath})) {
            return ResponseEntity.ok("新增成功！");
        } else {
            return ResponseEntity.ok("新增失败！");
        }
        } catch (Exception e) {
        logger.error(e.getMessage(),e);
        }
            return ResponseEntity.ok("新增异常！");
        }

    /**
     * ${table.comment}修改
     *
     * @param ${table.entityPath} ${entity} 对象
     * @return Result
     */
    <#if swagger2>
        @ApiOperation(value = "${table.comment}信息", notes = "修改")
    </#if>
    @ResponseBody
    @PutMapping("/update")
    <#--public ResultVo<String> update(@RequestBody ${entity} ${table.entityPath}) {
        ResultVo<String> result = new ResultVo<>();
            try {
                if (i${entity}Service.updateById(${table.entityPath})) {
                    result.resultSuccess("修改成功!");
                } else {
                    result.resultFail("修改失败!");
                }
            } catch (Exception e) {
                result.resultFail("修改异常！");
                logger.error(e.getMessage(),e);
            }
        return result;
    }-->
    public ResultVo<String> update(@RequestBody ${entity} ${table.entityPath}) {
        ResultVo<String> result = new ResultVo<>();
            try {
            if (i${entity}Service.updateById(${table.entityPath})) {
            return ResponseEntity.ok("修改成功!");
            } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改失败!");
            }
            } catch (Exception e) {
            logger.error(e.getMessage(),e);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改异常!");
    }

    /**
     * ${table.comment}根据ID查询信息
     *
     * @param id
     * @return Result
     */
    <#if swagger2>
    @ApiOperation(value = "${table.comment}信息", notes = "根据ID查询")
    </#if>
    @ResponseBody
    @GetMapping("/selectById")
    <#--public ResultVo<${entity}> selectById(@RequestParam String id) {
        ResultVo<${entity}> result = new ResultVo<>();
        try {
            ${entity} ${table.entityPath} = i${entity}Service.getById(id);
            result.resultSuccess(${table.entityPath});
            result.setMsg("查询成功！");
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(),e);
        }
        return result;
    }-->
    public ResponseEntity<${entity}> selectById(@RequestParam String id) {
        try {
            ${entity} ${table.entityPath} = i${entity}Service.getById(id);
            return ResponseEntity.ok(tGoodsCategory);
        } catch (Exception e) {
        logger.error(e.getMessage(),e);
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * ${table.comment}查询列表
     *
     * @param map
     * @return Result
     */
    <#if swagger2>
    @ApiOperation(value = "${table.comment}信息", notes = "查询列表")
    </#if>
    @ResponseBody
    @PostMapping("/list")
    <#--public ResultVo<List<Map<String, Object>>> list(HttpServletRequest request,
                                                                @RequestBody Map<String, Object> map) {
        ResultVo<List<Map<String, Object>>> result = new ResultVo<>();
        try {
            //map.put("currentPage", 1);
            //map.put("pageSize", 10);
            result = i${entity}Service.selectListByMap(map);
        } catch (Exception e) {
            result.resultFail("查询异常！");
            logger.error(e.getMessage(), e);
        }
        return result;
    }-->
    public ResponseEntity<List<Map<String, Object>>> list(HttpServletRequest request,
    @RequestBody Map<String, Object> map) {
    try {
            List<Map<String, Object>> list = i${entity}Service.selectListByMap(map);
            return ResponseEntity.ok(list);
    } catch (Exception e) {
    logger.error(e.getMessage(), e);
    }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
</#if>

